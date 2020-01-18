import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Compiler implements Opcodes {

    public static byte[] dump () throws Exception {

	ClassWriter classWriter = new ClassWriter(0);
	FieldVisitor fieldVisitor;
	RecordComponentVisitor recordComponentVisitor;
	MethodVisitor methodVisitor;
	AnnotationVisitor annotationVisitor0;

	classWriter.visit(V13, ACC_PUBLIC | ACC_SUPER, "HelloWorld", null, "java/lang/Object", null);

	classWriter.visitSource("HelloWorld.java", null);

	{
	    methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
	    methodVisitor.visitCode();
	    Label label0 = new Label();
	    methodVisitor.visitLabel(label0);
	    methodVisitor.visitLineNumber(1, label0);
	    methodVisitor.visitVarInsn(ALOAD, 0);
	    methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
	    methodVisitor.visitInsn(RETURN);
	    methodVisitor.visitMaxs(1, 1);
	    methodVisitor.visitEnd();
	}
	{
	    methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
	    methodVisitor.visitCode();
	    Label label0 = new Label();
	    methodVisitor.visitLabel(label0);
	    methodVisitor.visitLineNumber(6, label0);
	    methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
	    methodVisitor.visitLdcInsn("Hello, World ! from gen");
	    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
	    Label label1 = new Label();
	    methodVisitor.visitLabel(label1);
	    methodVisitor.visitLineNumber(7, label1);
	    methodVisitor.visitInsn(RETURN);
	    methodVisitor.visitMaxs(2, 1);
	    methodVisitor.visitEnd();
	}
	classWriter.visitEnd();

	return classWriter.toByteArray();
    }

    public static void main(String[] args)
    {

	byte[] data = null;
	try {
	    data = Compiler.dump();
	}
	catch (Exception e) {
	    System.out.println("Failed to dump code: " + e);
	    return;
	}
	    
	try
	{
	    File file = new File("HelloWorld.class");
	    FileOutputStream os = new FileOutputStream(file);
	    os.write(data);
	}
	catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while writing file " + ioe);
        }
	
    }
}
