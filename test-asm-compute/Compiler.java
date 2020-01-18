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

	classWriter.visit(V13, ACC_PUBLIC | ACC_SUPER, "Compute", null, "java/lang/Object", null);

	classWriter.visitSource("Compute.java", null);

	{
	    methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
	    methodVisitor.visitCode();
	    methodVisitor.visitVarInsn(ALOAD, 0);
	    methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
	    methodVisitor.visitInsn(RETURN);
	    methodVisitor.visitMaxs(1, 1);
	    methodVisitor.visitEnd();
	}
	
	{
	    methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "compute", "()I", null, null);
	    methodVisitor.visitCode();

	    methodVisitor.visitIntInsn(BIPUSH, 10);
	    methodVisitor.visitVarInsn(ISTORE, 0);
	    methodVisitor.visitIntInsn(BIPUSH, 5);
	    methodVisitor.visitVarInsn(ISTORE, 1);
	    methodVisitor.visitIntInsn(BIPUSH, 23);
	    methodVisitor.visitVarInsn(ISTORE, 2);
	    methodVisitor.visitIntInsn(BIPUSH, 67);
	    methodVisitor.visitVarInsn(ISTORE, 3);
	    methodVisitor.visitIntInsn(BIPUSH, 13);
	    methodVisitor.visitVarInsn(ISTORE, 4);
	    methodVisitor.visitVarInsn(ILOAD, 0);
	    methodVisitor.visitVarInsn(ILOAD, 1);
	    methodVisitor.visitVarInsn(ILOAD, 2);
	    methodVisitor.visitInsn(IMUL);
	    methodVisitor.visitVarInsn(ILOAD, 3);
	    methodVisitor.visitInsn(IDIV);
	    methodVisitor.visitVarInsn(ILOAD, 0);
	    methodVisitor.visitInsn(IREM);
	    methodVisitor.visitInsn(IADD);
	    methodVisitor.visitVarInsn(ILOAD, 4);
	    methodVisitor.visitInsn(ISUB);
	    methodVisitor.visitInsn(IRETURN);
	    
	    methodVisitor.visitMaxs(3, 5);
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
		File file = new File("Compute.class");
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
