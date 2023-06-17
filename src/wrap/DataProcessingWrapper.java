package wrap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import json.JsonUtil;

public class DataProcessingWrapper {

	static Object original = null; // 주어진 데이터
	static Object temp = null; // 문제를 풀기위한 임시 데이터
	static Object out = null; // output을 위한 데이터

	public static void solve() throws Exception {
		List test = new ArrayList();
		List Test2 = DataProcessingWrapper.<List>convert(original);
		Test2.add("");
	}

	public static void solve(HttpServletRequest request, HttpServletResponse response) {

	}

	public static <T> void prepareData(Object data) throws Exception {
		// original = DataProcessingWrapper.<T>convert(data);
		original = data;
	}

	private static <T> T convert(Object output) throws Exception {
		Class<?> objClass = output.getClass();
		T myObj;
		if (JsonObject.class.isAssignableFrom(objClass)) {
			myObj = (T) output;
		} else if (List.class.isAssignableFrom(objClass)) {
			myObj = (T) output;
		} else if (Map.class.isAssignableFrom(objClass)) {
			myObj = (T) output;
		} else if (String.class.isAssignableFrom(objClass)) {
			myObj = (T) output;
		} else {
			throw new Exception("impossible type casting");
		}

		//Type type = new TypeToken<T>() {}.getType();
		//type = ((ParameterizedType) type).getActualTypeArguments()[0];
		return (T) myObj;
	}

	public static void prepareData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuffer buffers = new StringBuffer();
		String line;
		while ((line = input.readLine()) != null) {
			buffers.append(line);
		}
		input.close();

		// original에 담기
		original = JsonUtil.getJsonObject(buffers.toString());
		// original type GObj
		// ((GObj)original).has(key)
	}

	public static <T> T genResponse() throws Exception {
		if (out == null)
			throw new Exception("output is null");
		return DataProcessingWrapper.<T>convert(out);
	}
}
