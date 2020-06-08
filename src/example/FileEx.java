package example;

import java.io.IOException;
import java.util.List;

import file.Reader;
import file.Writer;

public class FileEx {

	public static void main(String[] args) {
		Reader r = new Reader();
		try {
			r.openFile("C:\\eclipse\\workspace\\sample\\src\\file\\", "GLineNumberReader.java");
			r.readAllLine();
			List<String> al = r.docs;
			for(String s:al)
			{
				System.out.println(s);
			}
			
			Writer w = new Writer();
			w.openFile(".", "test.txt");
			w.writeLine(al);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*
		GLineNumberReader gl = new GLineNumberReader();
		try {
			gl.openFile("C:\\eclipse\\workspace\\sample\\src\\file\\", "GLineNumberReader.java");
			
			gl.readAllLine();
			ArrayList<String> al = gl.docs;
			for(String s:al)
			{
				System.out.println(s);
			}
		
			gl.resetLineReader(); //read all line 함수 호출후 readline 실행하려면 반드시 reset 필요함
			
			gl.readLine(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
