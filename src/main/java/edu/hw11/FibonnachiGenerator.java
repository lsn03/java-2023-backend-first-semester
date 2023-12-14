package edu.hw11;

import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import java.io.FileOutputStream;
import java.io.IOException;

public class FibonnachiGenerator {


    public static final int INDEX_OF_ONE_AT_EXIT = 1;
    public static final int FIB1_INDEX = 2;
    public static final int FIB2_INDEX = 3;
    public static final int INDEX_I = 4;
    public static final int TEMP_VAR_INDEX = 5;


    public static void generateFibClass(String filePath) throws IOException {
//        var uloadedType = new ByteBuddy()
//                .subclass(Object.class)
//                .name("MyFibonacci")
//                .defineMethod("fib",long.class, Opcodes.ACC_PUBLIC)
//                .withParameter(int.class, "n" )
//                .intercept(new FibMethodGen())
//                .make();

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Fibonacci", null, "java/lang/Object", null);

        // default constructor

        initDefaultConstructor(cw, mv);


        initPsfFieldStart(cw,mv);


        // create fib
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "fib", "(I)J", null, null);
        mv.visitCode();

        Label label1 = new Label();
//        if var1 <= 1 return var1 ;
        createConditionOfExitFromStart(mv, label1);

        mv.visitLabel(label1);

//        create temp vars fib1 = fib2 = 1
        CreateFib1Fib2EqualsOne(mv);

//        create for
        createFor(mv);
        mv.visitMaxs(6, 7);
        cw.visitEnd();

        byte[] bytes = cw.toByteArray();
        try (FileOutputStream fos = new FileOutputStream(filePath + "Fibonacci.class")) {
            fos.write(bytes);
        }

    }

    private static void createFor(MethodVisitor mv) {
        Label loopStart = new Label();


        mv.visitVarInsn(Opcodes.LLOAD, 1);
        mv.visitVarInsn(Opcodes.LSTORE, INDEX_I);
        mv.visitLabel(loopStart);

//       temp = fib1+fib2
        mv.visitVarInsn(Opcodes.LLOAD, FIB1_INDEX);
        mv.visitVarInsn(Opcodes.LLOAD, FIB2_INDEX);
        mv.visitInsn(Opcodes.LADD);
        mv.visitVarInsn(Opcodes.LSTORE, TEMP_VAR_INDEX);

        // Swap values
//        fib1 = fib2;
//        fib2 = temp;
        mv.visitVarInsn(Opcodes.LLOAD, FIB2_INDEX);
        mv.visitVarInsn(Opcodes.LSTORE, FIB1_INDEX);

        mv.visitVarInsn(Opcodes.LLOAD, TEMP_VAR_INDEX);
        mv.visitVarInsn(Opcodes.LSTORE, FIB2_INDEX);

        // I++;
        mv.visitIincInsn(INDEX_I, 1);

        // if (i <= n)
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_I);
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_OF_ONE_AT_EXIT);
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, loopStart); //  goto loopStart

        // return fib2
        mv.visitVarInsn(Opcodes.LLOAD, FIB2_INDEX);
        mv.visitInsn(Opcodes.LRETURN);
    }



    private static void initDefaultConstructor(ClassWriter cw, MethodVisitor mv) {

//      public Fibonacci() {}

        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }
    private static void initPsfFieldStart(ClassWriter cw, MethodVisitor mv) {
//         private static final int START = 3;

        cw.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL, "START", "I", null, 3).visitEnd();
        mv.visitCode();
        mv.visitIntInsn(Opcodes.SIPUSH, 3);
        mv.visitFieldInsn(Opcodes.PUTSTATIC, "Fibonacci", "START", "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }
    private static void createConditionOfExitFromStart(MethodVisitor mv, Label label1) {
//        if var1 <= 1 return var
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_OF_ONE_AT_EXIT);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, label1);
        mv.visitVarInsn(Opcodes.ILOAD, INDEX_OF_ONE_AT_EXIT);
        mv.visitInsn(Opcodes.I2L);
        mv.visitInsn(Opcodes.LRETURN);
    }

    private static void CreateFib1Fib2EqualsOne(MethodVisitor mv) {
        mv.visitInsn(Opcodes.LCONST_1);
        mv.visitVarInsn(Opcodes.LSTORE, FIB1_INDEX);
        mv.visitInsn(Opcodes.LCONST_1);
        mv.visitVarInsn(Opcodes.LSTORE, FIB2_INDEX);

    }

    public static void main(String[] args) throws IOException {
        generateFibClass("src/main/");
    }


}
