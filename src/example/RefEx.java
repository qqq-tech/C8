package example;

import java.util.HashMap;

import ref.Refelection;

public class RefEx {

	public static void main(String []args)
	{
		HashMap<String, String> test = new HashMap<>();
		test.put("1","1");
		test.put("2","2");
		test.put("3","3");
		HashMap<String, String> test1 = new HashMap<>();
		//Refelection.copyFields(test, test1);
		
		for(String k: test1.keySet())
		{
			System.out.println(k);
		}
		
		
		//invoke method
		int []a=new int[] {1,2};
		Refelection rf= new Refelection();
		rf.invokeClassMethod("EXT/com/test/lgcns/Calc.clasas", "com.test.lgcns.Calc", "add", a.getClass(), a);
	}
}
