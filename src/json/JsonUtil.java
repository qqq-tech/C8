package json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil<T> {
	public static <T> T convertToObj(String from, Class<T> to ) {
		Gson gson = new Gson();
		return gson.fromJson(from, to);
	}
	
	public static <T> T convertToObj(JsonObject jobj, Class<T> to ) {
		Gson gson = new Gson();
		return gson.fromJson(jobj, to);
	}
	
	public static <T> String convertToJson(Class<T> from) {
		Gson gson = new Gson();
		return gson.toJson(from);
	}
	
	public static GObj getJsonObject(String json) {
		Gson gson = new Gson();
		JsonObject obj= JsonParser.parseString(json).getAsJsonObject();
		GObj jobj = new GObj();
		jobj.obj=obj;
		return jobj;
	}
	
	
	//public JObjUtil<T> doInBackground(Class<T> type, String json, Void... params) {
//	public static <T>T doInBackground(Class<T> type, String json) {
//	    GsonBuilder gson = new GsonBuilder();
//	    Type collectionType = new TypeToken<JObjUtil<T>>(){}.getType();
//	    T myJson = gson.create().fromJson(json, collectionType);
//	    return myJson;
//	}
	
	public void testJson() {
		Gson gson = new Gson();
		String json = "{\"nLeg\":4, \"name\": \"Bill\"}";
		//Animal animal = gson.fromJson(json, Animal.class);
		//System.out.println(animal);
		//String reJson = gson.toJson(animal);
		//System.out.println(reJson);
	}
	
	public static void create() {

		Gson gson = new Gson();
		JsonParser.parseString("");
		// Json key, value 추가
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "anna");
		jsonObject.addProperty("id", 1);

		// JsonObject를 Json 문자열로 변환
		String jsonStr = gson.toJson(jsonObject);

		// 생성된 Json 문자열 출력
		System.out.println(jsonStr); // {"name":"anna","id":1}
	}
	
}
