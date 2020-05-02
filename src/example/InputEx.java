package example;

import java.io.IOException;

import input.ConsoleReader;
import input.ConsoleScanner;

public class InputEx {
	public static void main(String[] args) {
		ConsoleReader cr = new ConsoleReader();
		try {
			cr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConsoleScanner cs =new ConsoleScanner();
		cs.readLine();
		cs.readInt();
	}
}
