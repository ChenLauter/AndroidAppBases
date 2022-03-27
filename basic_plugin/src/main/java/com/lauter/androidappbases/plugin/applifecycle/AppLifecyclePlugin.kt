package com.lauter.androidappbases.plugin.applifecycle

import com.android.build.gradle.BaseExtension
import com.lauter.androidappbases.plugin.applifecycle.AppLifecycleConst.APP_LIFECYCLE_CALLBACK
import com.lauter.androidappbases.plugin.applifecycle.AppLifecycleConst.APP_LIFECYCLE_MANAGER_FILE
import com.lauter.androidappbases.plugin.applifecycle.AppLifecycleConst.APP_LIFECYCLE_PLUGIN_NAME
import com.lauter.androidappbases.plugin.applifecycle.AppLifecycleConst.APP_LIFECYCLE_PROXY_SUFFIX
import com.lauter.androidappbases.plugin.base.BaseTransform
import com.lauter.androidappbases.plugin.utils.JarHandler
import com.lauter.androidappbases.plugin.utils.PluginUtil
import com.lauter.androidappbases.plugin.utils.isClass
import org.apache.commons.compress.utils.IOUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import java.io.File

class AppLifecyclePlugin : BaseTransform(), Plugin<Project> {

    override fun getName(): String = APP_LIFECYCLE_PLUGIN_NAME

    private val appLifecycleClassNames = mutableListOf<String>()
    private var appLifecyclesJar:File? = null

    override fun transformFile(file: File) {
        val classReader = ClassReader(file.readBytes())
        val name = classReader.className
        if (name.endsWith(APP_LIFECYCLE_PROXY_SUFFIX) &&
            classReader.interfaces.contains(APP_LIFECYCLE_CALLBACK)
        ) {
            appLifecycleClassNames.add(name)
        }
    }

    override fun transformJar(inputJar: File, outputJar: File) {
        JarHandler.create(inputJar, outputJar).filter { _, jarEntity ->
            if(jarEntity.name == APP_LIFECYCLE_MANAGER_FILE){
                appLifecyclesJar = outputJar
            }
            jarEntity.name.isClass() && jarEntity.name.endsWith("$APP_LIFECYCLE_PROXY_SUFFIX.class")
        }.map { _, inputStream ->
            val byteArray = IOUtils.toByteArray(inputStream)
            val classReader = ClassReader(byteArray)
            if (classReader.className.endsWith(APP_LIFECYCLE_PROXY_SUFFIX) && classReader.interfaces.contains(
                    APP_LIFECYCLE_CALLBACK
                )
            ) {
                appLifecycleClassNames.add(classReader.className)
            }
            byteArray
        }
    }

    override fun onTransformComplete() {
        if(appLifecyclesJar == null){
            PluginUtil.log("错误：没有找到包含${APP_LIFECYCLE_MANAGER_FILE}的jar")
            return
        }
        PluginUtil.log("找到包含${APP_LIFECYCLE_MANAGER_FILE}的jar")
        PluginUtil.log("appLifecycleClassNames: $appLifecycleClassNames")
        val targetJar = appLifecyclesJar!!
        // 创建临时jar
        val file = File(targetJar.parent,targetJar.nameWithoutExtension+"_temp.jar")
        if(file.exists()){
            file.delete()
        }
        targetJar.copyTo(file,true)
        JarHandler(file,targetJar).filter { _, jarEntity ->
            jarEntity.name == APP_LIFECYCLE_MANAGER_FILE
        }.doComplete {
            file.delete()
        }.map { _, inputStream ->
            val classReader = ClassReader(
                IOUtils.toByteArray(inputStream)
            )
            val classWriter = ClassWriter(
                classReader,
                ClassWriter.COMPUTE_MAXS
            )
            val cv: ClassVisitor = AppLifecycleVisitor(classWriter,appLifecycleClassNames)
            classReader.accept(cv, ClassReader.EXPAND_FRAMES)
            classWriter.toByteArray()
        }
    }

    override fun apply(project: Project) {
        PluginUtil.log("${this.name} on apply")
        project.extensions.run {
            findByType(BaseExtension::class.java)?.run {
                PluginUtil.log("${this@AppLifecyclePlugin.name} on register transform ${this}")
                registerTransform(this@AppLifecyclePlugin)
            }
        }
    }

}