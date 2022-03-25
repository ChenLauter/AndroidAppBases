package com.lauter.androidappbases.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.ide.common.internal.WaitableExecutor
import com.android.utils.FileUtils
import java.io.File
import java.util.concurrent.Callable

abstract class BaseTransform : Transform() {

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean = true

    override fun transform(invocation: TransformInvocation?) {
        invocation?:return
        val executor = WaitableExecutor.useGlobalSharedThreadPool()
        val dirInput = mutableListOf<DirectoryInput>()
        val jarInput = mutableListOf<JarInput>()
        invocation.inputs.forEach {
            dirInput.addAll(it.directoryInputs)
            jarInput.addAll(it.jarInputs)
        }
        dirInput.forEach(invocation) { input, output ->
            val inputDir = input.file.absolutePath
            val outputDir = output.absolutePath
            if (invocation.isIncremental) {
                input.changedFiles.filter { it.key.isClass() }.forEach { (file, status) ->
                    if (status in arrayOf(Status.CHANGED, Status.ADDED)) {
                        executor.execute {
                            val outputFile = File(file.absolutePath.replace(inputDir, outputDir))
                            FileUtils.deleteIfExists(outputFile)
                            file.copyTo(outputFile)
                            PluginUtil.log("transform $outputFile with incremental!")
                            transformFile(outputFile)
                        }
                    }
                }
            } else {
                output.deleteRecursively()
                input.file.copyRecursively(output)
                output.walk().filter { it.isClass() }
                    .forEach {file ->
                        executor.execute {
                            PluginUtil.log("transform $file without incremental!")
                            transformFile(file)
                        }
                    }
            }
        }
        jarInput.forEach(invocation) { input, output ->
            if(invocation.isIncremental){
                when(input.status){
                    Status.REMOVED -> {
                        FileUtils.deleteIfExists(input.file)
                    }
                    in arrayOf(Status.CHANGED, Status.ADDED) -> {
                        PluginUtil.log("transformJar ${input.file} with incremental!")
                        executor.execute(TransformJarTask(input.file,output))
                    }
                }
            } else {
                PluginUtil.log("transformJar ${input.file} without incremental!")
                executor.execute(TransformJarTask(input.file,output))
            }
        }
        executor.waitForTasksWithQuickFail<Any>(true)
        onTransformComplete()
    }

    inner class TransformJarTask(private val inputJar:File, private val outputJar:File) :
        Callable<Any?> {
        override fun call(): Any? {
            FileUtils.deleteIfExists(outputJar)
            transformJar(inputJar, outputJar)
            return null
        }
    }

    abstract fun transformFile(file: File)

    abstract fun transformJar(inputJar: File, outputJar: File)

    abstract fun onTransformComplete()
}