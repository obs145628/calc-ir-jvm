import java.io.FileNotFoundException;
import java.io.IOException;

public class CompilerAPI
{

    private static JVMCompiler cl_instance = null;

    private static JVMCompiler cl()
    {
	if (cl_instance == null)
	    cl_instance = new JVMCompiler();
	return cl_instance;
    }

    

    public static void setClassName(String className) {
	cl().setClassName(className);
    }

    public static void begin() throws RuntimeException {
	cl().begin();
    }

    public static void end() throws RuntimeException {
        cl().end();
    }

    public static byte[] getBytes() throws RuntimeException {
	return cl().getBytes();
    }

    public static void saveToFile() throws RuntimeException, FileNotFoundException, IOException {
	cl().saveToFile();
    }

    public static void i_bipush(int val)
    {
	cl().i_bipush(val);
    }

    public static void i_istore(int pos) {
	cl().i_istore(pos);
    }

    public static void i_iload(int pos) {
	cl().i_iload(pos);
    }

    public static void i_iadd() {
	cl().i_iadd();
    }

    public static void i_isub() {
	cl().i_isub();
    }

    public static void i_imul() {
	cl().i_imul();
    }

    public static void i_idiv() {
	cl().i_idiv();
    }

    public static void i_irem() {
	cl().i_irem();
    }

    public static void i_ireturn() {
	cl().i_ireturn();
    }

    public static void setMaxs(int maxStack, int maxLocals)
    {
	cl().setMaxs(maxStack, maxLocals);
    }
}
