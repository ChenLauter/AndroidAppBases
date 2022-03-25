package com.lauter.androidappbases.plugin

import com.lauter.androidappbases.plugin.Constant.APP_LIFECYCLE_MANAGER
import com.lauter.androidappbases.plugin.Constant.APP_LIFECYCLE_REGISTER_METHOD_NAME
import com.lauter.androidappbases.plugin.Constant.INIT
import com.lauter.androidappbases.plugin.Constant.INIT_DESC
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class AppLifecycleVisitor(classVisitor: ClassVisitor,
                          private val callbacks: List<String>) : ClassVisitor(Opcodes.ASM9,classVisitor) {


    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var visitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (INIT == name && INIT_DESC == descriptor && access and Opcodes.ACC_PRIVATE != 0) {
            visitor = object : AdviceAdapter(ASM9, visitor, access, name, descriptor) {
                override fun onMethodExit(opcode: Int) {
                    for (item in callbacks) {
                        mv.visitVarInsn(ALOAD, 0)
                        mv.visitLdcInsn(item.replace("/", "."))
                        mv.visitMethodInsn(
                            INVOKESPECIAL,
                            APP_LIFECYCLE_MANAGER,
                            APP_LIFECYCLE_REGISTER_METHOD_NAME,
                            "(Ljava/lang/String;)V",
                            false
                        )
                    }
                }
            }
        }
        return visitor
    }
}