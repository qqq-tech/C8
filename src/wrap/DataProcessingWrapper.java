package wrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

public class DataProcessingWrapper {
	
	static Object original = null;
	static Object temp = null;
	static Object out = null;
	
	public static void solve() throws Exception {
		List test = new ArrayList();
		List Test2 = DataProcessingWrapper.<List>convert(test);
		Test2.add("");
	}
	
	public static void prepareData(String data)  throws Exception {
		
	}

	public static void prepareData(List<String> data) throws Exception {
		
	}

	public static void prepareData(Map data)  throws Exception{

	}

	private static <T> T convert(Object target) throws Exception {
		/*
		Class<?> objClass = target.getClass();
		if (JsonObject.class.isAssignableFrom(objClass)) {
			JsonObject myObj = (JsonObject) target; 
		} else if (List.class.isAssignableFrom(objClass)) {
			List myObj = (List) target;
		} else if (Map.class.isAssignableFrom(objClass)) {
			Map myObj = (Map) target;
		}else if (String.class.isAssignableFrom(objClass)) {
			String myObj = (String) target;
		}else {
			throw new Exception("impossible type casting");
		}
		*/
		return (T)target;
	}

	public static void prepareData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		   BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
           StringBuffer buffers= new StringBuffer();
           String line;
           while ((line= input.readLine()) != null) {
           	buffers.append(line);
           } 
           input.close(); 
           
           //original¿¡ ´ã±â
		
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	public static <T> T genResponse() throws Exception {
		if(out==null) throw new Exception("output is null");
		return DataProcessingWrapper.<T>convert(out);
	}
}
