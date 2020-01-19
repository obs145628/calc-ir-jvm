public class TestAPI
{

    public static void main(String[] args)
    {
	try
	{
	    CompilerAPI.setClassName("App");
	    CompilerAPI.begin();

	    CompilerAPI.i_bipush(10);
	    CompilerAPI.i_istore(0);
	    CompilerAPI.i_bipush(5);
	    CompilerAPI.i_istore(1);
	    CompilerAPI.i_bipush(23);
	    CompilerAPI.i_istore(2);
	    CompilerAPI.i_bipush(67);
	    CompilerAPI.i_istore(3);
	    CompilerAPI.i_bipush(13);
	    CompilerAPI.i_istore(4);
	    CompilerAPI.i_iload(0);
	    CompilerAPI.i_iload(1);
	    CompilerAPI.i_iload(2);
	    CompilerAPI.i_imul();
	    CompilerAPI.i_iload(3);
	    CompilerAPI.i_idiv();
	    CompilerAPI.i_iload(0);
	    CompilerAPI.i_irem();
	    CompilerAPI.i_iadd();
	    CompilerAPI.i_iload(4);
	    CompilerAPI.i_isub();
	    CompilerAPI.i_ireturn();

	    CompilerAPI.setMaxs(3, 5);
	    CompilerAPI.end();
	    CompilerAPI.saveToFile();
	}

	catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
