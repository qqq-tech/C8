package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;

public class GLineNumberReader {
	private LineNumberReader lr = null;
	public List<String> docs;
	public GLineNumberReader() {

	}

	public void openFile(String path, String name) throws FileNotFoundException {
		if(lr!=null) return;
		
		File inFile = new File(path, name);
		lr = new LineNumberReader(new FileReader(inFile));
		docs = new LinkedList<>();
	}

	public void finalize() throws IOException {
		if (lr != null)
			lr.close();
	}
	
	public void resetLineReader()
	{
		try {
			lr.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine(int lineNumber) {
		if(lineNumber == 0 )
			System.out.println("first line is 1");
		
		String line = "";
		try {
			lr.setLineNumber(lineNumber);
			line = lr.readLine();
			docs.set(lineNumber-1, line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	public String readAllLine() throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			while ((line = lr.readLine()) != null) {
				sb.append(line);
				docs.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
