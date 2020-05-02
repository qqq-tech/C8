package example;

import java.io.IOException;

import proc.ProcRun;

public class ProcEx {
	public static void main(String[] args) {
		try {
			ProcRun.run(new String[] {"cmd","/c", "dir /w"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
