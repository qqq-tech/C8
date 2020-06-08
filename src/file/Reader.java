package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Reader {
	private BufferedReader br = null;
	public List<String> docs;
	public Reader() {
		
	}
	
	public void openFile(String path,String name) throws FileNotFoundException
	{
		File inFile  = new File(path, name);
		br = new BufferedReader(new FileReader(inFile));
		docs=new LinkedList<>();
	}
	public void finalize() throws IOException {
		if(br!=null)
			br.close();
	}

	public String readAllLine() throws IOException {
		StringBuilder sb= new StringBuilder();
	        try {
	            String line;
	            while ((line = br.readLine()) != null) {
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
