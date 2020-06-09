package net;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultInterface {
	private static HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();

	public synchronized static void setThreadID(String threadID) {
		res.put(threadID, new ArrayList<>());
	}

	public synchronized static void setResult(String threadID, String result) {
		ArrayList<String> ar = res.get(threadID);
		ar.add(result);
		res.put(threadID, ar);
	}

	public synchronized static ArrayList<String> getResult(String threadID, boolean autoRemove) {
		ArrayList<String> ar= res.get(threadID);
		if (autoRemove)
			res.remove(threadID);
		return ar;
	}

	public synchronized static void removeResult(String threadID) {
		res.remove(threadID);
	}

	public static void doRun(String threadID) {
		
	}
}
