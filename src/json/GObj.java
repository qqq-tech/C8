package json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

//public class JObjUtil<T> {
public class GObj {
	JsonObject obj;
	Gson gson;

	public GObj() {
		obj = new JsonObject();
		gson = new Gson();
	}

	public String getString(String key) {
		return obj.get(key).getAsString();
	}

	public int getInt(String key) {
		return obj.get(key).getAsInt();
	}

	public boolean has(String key) {
		return obj.has(key);
	}

	public ArrayList getArray(String jsonStr, Class type) {
		// Type type = new TypeToken<List<String>>() {}.getType();
		return gson.fromJson(jsonStr, TypeToken.getParameterized(ArrayList.class, type).getType());
	}

	public void addString(String key, String value) {
		obj.addProperty(key, value);
	}

	public void addInt(String key, int value) {
		obj.addProperty(key, value);
	}

	public void addArray(String key, List<?> value) {
		obj.addProperty(key, gson.toJson(value));
	}

}
