package com.lauter.androidappbases.apt.processor.applifecycle

import com.google.auto.service.AutoService
import com.lauter.androidappbases.apt.annotation.AppLifecycle
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class)
internal class AppLifecycleProcessor : AbstractProcessor() {

    private lateinit var elements: Elements
    private lateinit var filer: Filer

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        filer = processingEnv.filer
        elements = processingEnv.elementUtils
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_8
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(AppLifecycle::class.java.canonicalName)
    }

    override fun process(p0: MutableSet<out TypeElement>, environ: RoundEnvironment): Boolean {
        environ.getElementsAnnotatedWith(AppLifecycle::class.java)
            .filter { it.kind == ElementKind.CLASS }
            .filter {
                (it as TypeElement).interfaces.contains(
                    elements.getTypeElement(AppLifecycleConst.CALLBACK_QUALIFIED_NAME).asType()
                )
            }
            .forEach {
                AppLifecycleProxyCreator(it as TypeElement, elements).build().run {
                    writeTo(filer)
                }

            }
        return true
    }

}