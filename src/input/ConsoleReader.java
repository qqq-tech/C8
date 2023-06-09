package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
	private InputStreamReader in = null;
	private BufferedReader br = null;

	public ConsoleReader() {
		in = new InputStreamReader(System.in);
		br = new BufferedReader(in);
	}

	public void finalize() throws IOException {
		if (br != null)
			br.close();
		if (in != null)
			in.close();
	}

	public String readLine() throws IOException {
		String str = br.readLine();
//		System.out.println("reader value:" + str);
		return str;
	}

}
