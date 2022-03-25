package com.lauter.androidappbases.apt.processor

import com.squareup.kotlinpoet.*
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

internal class AppLifecycleProxyCreator(private val typeElement: TypeElement, private val elements: Elements) {


    private val packageName = elements.getPackageOf(typeElement).qualifiedName.toString()
    private val fileName = "${Constant.PROXY_PREFIX}${typeElement.simpleName}${Constant.PROXY_SUFFIX}"
    private val contextType = elements.getTypeElement(Constant.CONTEXT).asClassName()
    private val superInterface = elements.getTypeElement(Constant.CALLBACK_QUALIFIED_NAME).asClassName()

    fun build(): FileSpec {
        return FileSpec.builder(packageName, fileName)
            .addType(getTypeSpec())
            .build()
    }

    private fun getTypeSpec(): TypeSpec {
        return TypeSpec.classBuilder(fileName)
            .addProperty(getProperty())
            .addSuperinterface(superInterface)
            .addFunction(getOnCreate())
            .addFunction(getPriority())
            .build()
    }

    private fun getProperty(): PropertySpec {
        return PropertySpec.builder("callback", typeElement.asClassName(), KModifier.PRIVATE)
            .initializer("%T()", typeElement.asType())
            .build()
    }

    private fun getOnCreate(): FunSpec {
        return FunSpec.builder("onCreate")
            .addModifiers(KModifier.OVERRIDE)
            .addParameter("context", contextType)
            .addStatement("callback.onCreate(context)")
            .build()
    }

    private fun getPriority(): FunSpec {
        return FunSpec.builder("getPriority")
            .addModifiers(KModifier.OVERRIDE)
            .returns(Int::class)
            .addStatement("return callback.getPriority()")
            .build()
    }
}