package edu.hw11;

import java.io.FileOutputStream;
import java.io.IOException;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public final class FibonacciGenerator {


    private static final int INDEX_OF_ONE_AT_EXIT = 1;
    private static final int FIB1_INDEX = 2;
    private static final int FIB2_INDEX = 3;
    private static final int INDEX_I = 4;
    private static final int TEMP_VAR_INDEX = 5;
    private static final String DESCRIPTOR_GET_EMPTY_RETURN_VOID = "()V";
    private static final String JAVA_LANG_OBJECT = "java/lang/Object";
    private static final int MAX_STACK = 6;
    private static final int MAX_LOCALS = 7;
    private static final String INIT = "<init>";

    private FibonacciGenerator() {
    }

    public static void generateFibClass(String filePath) throws IOException {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, INIT, DESCRIPTOR_GET_EMPTY_RETURN_VOID, null, null);

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Fibonacci", null, JAVA_LANG_OBJECT, null);

        // default constructor

        initDefaultConstructor(cw, mv);


        // create fib
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "fib", "(I)I", null, null);
        mv.visitCode();

        Label label1 = new Label();
//        if var1 <= 1 return var1 ;
        createConditionOfExitFromStart(mv, label1);

        mv.visitLabel(label1);

//        create temp vars fib1 = fib2 = 1
        createFib1Fib2EqualsOne(mv);

//        create for
        createFor(mv);
        mv.visitMaxs(MAX_STACK, MAX_LOCALS);
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        try (FileOutputStream fos = new FileOutputStream(filePath + "Fibonacci.class")) {
            fos.write(bytes);
        }

    }

    private static void createFor(MethodVisitor mv) {
        Label loopStart = new Label();


        mv.visitInsn(Opcodes.ICONST_3);
        mv.visitVarInsn(Opcodes.ISTORE, INDEX_I);
        mv.visitLabel(loopStart);

//       temp = fib1+fib2
        mv.visitVarInsn(Opcodes.ILOAD, FIB1_INDEX);
        mv.visitVarInsn(Opcodes.ILOAD, FIB2_INDEX);
        mv.visitInsn(Opcodes.IADD);
        mv.visitVarInsn(Opcodes.ISTORE, TEMP_VAR_INDEX);

        // Swap values
//        fib1 = fib2;
//        fib2 = temp;
        mv.visitVarInsn(Opcodes.ILOAD, FIB2_INDEX);
        mv.visitVarInsn(Opcodes.ISTORE, FIB1_INDEX);

        mv.visitVarInsn(Opcodes.ILOAD, TEMP_VAR_INDEX);
        mv.visitVarInsn(Opcodes.ISTORE, FIB2_INDEX);

        // i++;
        mv.visitIincInsn(INDEX_I, 1);

        // if (i <= n)
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_I);
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_OF_ONE_AT_EXIT);
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, loopStart); //  goto loopStart


        // return fib2
        mv.visitVarInsn(Opcodes.ILOAD, FIB2_INDEX);
        mv.visitInsn(Opcodes.IRETURN);
    }


    private static void initDefaultConstructor(ClassWriter cw, MethodVisitor mv) {

//      public Fibonacci() {}

        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, JAVA_LANG_OBJECT, INIT, DESCRIPTOR_GET_EMPTY_RETURN_VOID, false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private static void createConditionOfExitFromStart(MethodVisitor mv, Label label1) {
//        if var1 <= 2 return 1
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_OF_ONE_AT_EXIT);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, label1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.IRETURN);
    }

    private static void createFib1Fib2EqualsOne(MethodVisitor mv) {
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, FIB1_INDEX);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, FIB2_INDEX);

    }


}
