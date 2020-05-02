package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
	private BufferedWriter bw = null;
	private File outFile = null;

	public Writer() {

	}

	public void openFile(String path, String name) throws IOException {
		outFile = new File(path, name);
		bw = new BufferedWriter(new FileWriter(outFile));
	}

	public void finalize() throws IOException {
		if (bw != null)
			bw.close();
	}
	public void writeLine(ArrayList<String> docs) throws IOException {
		for(String doc:docs)
		{
			if(!writeLine(doc))
			{
				System.out.println("File 쓰기 오류");
				throw new IOException();
			}
		}
		
	}
	public boolean writeLine(String line) throws IOException {
		boolean isSuccess = false;
		try {
			bw.write(line);
			bw.newLine();
			bw.flush();
			isSuccess = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
