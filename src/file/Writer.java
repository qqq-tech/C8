package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

	public void writeLine(List<String> docs) throws IOException {
		try
		{
			for (String doc : docs) {
				if (!writeLine(doc)) {
					System.out.println("File 쓰기 오류");
					throw new IOException();
				}
			}
		}
		catch(Exception e)
		{
			throw new IOException();
		}
		finally
		{
			bw.flush();
		}
	}

	public boolean writeLine(String line) throws IOException {
		boolean isSuccess = false;
		try {
			bw.write(line);
			bw.newLine();
			isSuccess = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
