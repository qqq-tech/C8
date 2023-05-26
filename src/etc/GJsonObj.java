package etc;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

//public class JObjUtil<T> {
public class GJsonObj {
	JsonObject obj;

	public String getString(String key) {
		return obj.get(key).getAsString();
	}

	public int getInt(String key) {
		return obj.get(key).getAsInt();
	}

	public Object getArray(String key, Class type) {
		Gson gson = new Gson();
        //Type type = new TypeToken<List<String>>() {}.getType();
        List<Object> l =  (List<Object>)gson.fromJson(obj.get(key).getAsString(), type);
        return l;
	}

	public void addString(String key, String value) {
		obj.addProperty(key, value);
	}

	public void addInt(String key, int value) {
		obj.addProperty(key, value);
	}

	public void addArray(String key, List<?> value) {
		Gson gson = new Gson();
		obj.addProperty(key, gson.toJson(value));
	}

}
