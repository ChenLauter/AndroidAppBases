package com.lauter.androidappbases.apt.processor.navigation

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class NavGraphNameProcessor : AbstractProcessor() {

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        processingEnv?.options?.get("nav.graph.name")?.run {
            FileSpec.builder(NavConst.PACKAGE,NavConst.CLASS_NAME)
                .addType(getType(this))
                .build()
                .writeTo(processingEnv.filer)
        }
    }

    private fun getType(name: String): TypeSpec {
        return TypeSpec.classBuilder(NavConst.CLASS_NAME)
            .addProperty(getProperty(name))
            .build()
    }

    private fun getProperty(name: String): PropertySpec {
        return PropertySpec.builder(NavConst.FIELD_NAME, String::class.java.kotlin)
            .initializer("\"$name\"")
            .build()
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_8
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        return true
    }
}