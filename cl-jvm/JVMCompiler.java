
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

public class JVMCompiler implements Opcodes {

    private enum State {
	PREPARE,
	CODE,
	FINISH
    };

    private byte[] _data;
    private State _state;
    private String _className;
    private ClassWriter _classWriter;
    private MethodVisitor _runMV;

    public JVMCompiler()
    {
	_data = null;
	_state = State.PREPARE;
	_className = null;
	_classWriter = null;
	_runMV = null;
    }

    public void setClassName(String className) {
	_className = className;
    }

    public void begin() throws RuntimeException {
	if (_className == null) {
	    throw new RuntimeException("Invalid call to begin(): className not set");
	}
	if (_state != State.PREPARE) {
	    throw new RuntimeException("begin() has already been called");
	}

	_state = State.CODE;
	_createClassWriter();
	_createInitMethod();
	_beginRunMethod();
    }

    public void end() throws RuntimeException {
	if (_state != State.CODE) {
	    throw new RuntimeException("end() can be called only after calling begin(), and only once");
	}

	_state = State.FINISH;
	_endRunMethod();
	_finishClass();
    }

    public byte[] getBytes() throws RuntimeException {
	if (_state != State.FINISH) {
	    throw new RuntimeException("getBytes() can only be called after calling end()");
	}

	return _data;
    }

    public void saveToFile() throws RuntimeException, FileNotFoundException, IOException {
	if (_state != State.FINISH) {
	    throw new RuntimeException("saveToFile() can only be called after calling end()");
	}


	File file = new File(_className + ".class");
	FileOutputStream os = new FileOutputStream(file);
	os.write(_data);
    }



    public void i_bipush(int val)
    {
       _runMV.visitIntInsn(BIPUSH, val);
    }

    public void i_istore(int pos) {
	_runMV.visitVarInsn(ISTORE, pos);
    }

    public void i_iload(int pos) {
	_runMV.visitVarInsn(ILOAD, pos);
    }

    public void i_iadd() {
	_runMV.visitInsn(IADD);
    }

    public void i_isub() {
	_runMV.visitInsn(ISUB);
    }

    public void i_imul() {
	_runMV.visitInsn(IMUL);
    }

    public void i_idiv() {
	_runMV.visitInsn(IDIV);
    }

    public void i_irem() {
	_runMV.visitInsn(IREM);
    }

    public void i_ireturn() {
	_runMV.visitInsn(IRETURN);
    }

    public void setMaxs(int maxStack, int maxLocals)
    {
	_runMV.visitMaxs(maxStack, maxLocals);
    }


    private void _createClassWriter() {
	_classWriter = new ClassWriter(0);
	_classWriter.visit(V13, ACC_PUBLIC | ACC_SUPER, _className, null, "java/lang/Object", null);
	_classWriter.visitSource(_className + ".java", null);
    }

    private void _finishClass() {
	_classWriter.visitEnd();
	_data = _classWriter.toByteArray();
    }

    private void _createInitMethod() {
	MethodVisitor mv = _classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
	mv.visitCode();
	mv.visitVarInsn(ALOAD, 0);
	mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
	mv.visitInsn(RETURN);
	mv.visitMaxs(1, 1);
	mv.visitEnd();
    }

    private void _beginRunMethod() {
	_runMV = _classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "run", "()I", null, null);
	_runMV.visitCode();
    }

    private void _endRunMethod() {
	_runMV.visitEnd();
    }






    /*
    public static byte[] dump () throws Exception {

	ClassWriter classWriter = new ClassWriter(0);
	MethodVisitor methodVisitor;

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
    */
    
}
