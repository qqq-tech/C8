package proc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcRun {

	public static String run(String[] cmd) throws IOException {
		//String addr = "http://www.naver.com";
		// String[] cmd = new String[] { "rundll32", "url.dll", "FileProtocolHandler",
		// addr };
		//Process process = new  ProcessBuilder(cmd).start();
		Process process = null;
		String str = null;
		BufferedReader stdOut=null;
		StringBuilder sb = new StringBuilder();
		
		try {
			// 프로세스 빌더를 통하여 외부 프로그램 실행
			process = new ProcessBuilder(cmd).start();

			// 외부 프로그램의 표준출력 상태 버퍼에 저장
			stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

			// 표준출력 상태를 출력
			while ((str = stdOut.readLine()) != null) {
				System.out.println(str);
				sb.append(str);
			}
			
		} catch (IOException e) {
			e.printStackTrace();

		}
		finally {
			if(stdOut!=null)
				stdOut.close();
		}
		
		return sb.toString();
	}
}
