package etc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirSearch {
	public static Map<String, HashMap<String, String>> result = new HashMap<String, HashMap<String, String>>();
	public static void subDirList(String source){
		File dir = new File(source); 
		File[] fileList = dir.listFiles(); 
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile()){
					// 파일이 있다면 파일 이름 출력
					//System.out.println("\t 파일 이름 = " + file.getName());
					HashMap<String, String> value = new HashMap<>();
					value.put("canonicalPath", file.getCanonicalPath());
					value.put("absolutePath", file.getAbsolutePath());
					value.put("path", file.getPath());
					result.put(file.getName(), value);
				}else if(file.isDirectory()){
					//System.out.println("디렉토리 이름 = " + file.getName());
					// 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString()); 
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
