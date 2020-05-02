package input;

import java.util.Scanner;

public class ConsoleScanner {
	private Scanner scanner = null;

	public ConsoleScanner() {
		scanner = new Scanner(System.in); // System.in - 표준 입력장치
	}

	public void finalize() {
		if (scanner != null)
			scanner.close();
	}

	public String readLine() {
		String str = scanner.nextLine();
		System.out.println("reader value:" + str);
		return str;
	}

	public int readInt() {
		int res = scanner.nextInt();
		System.out.println("reader value:" + res);
		return res;
	}

	public double readDouble() {
		double res = scanner.nextDouble();
		System.out.println("reader value:" + res);
		return res;
	}

}
