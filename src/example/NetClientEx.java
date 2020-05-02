package example;

import input.ConsoleReader;
import net.Client;

public class NetClientEx {
	public static void main(String[] args) {
		Client c = new Client();
		c.connect("127.0.0.1", 8888);
		ConsoleReader cr= new ConsoleReader();
		while(true)
		{
			try {
				String s= cr.readLine();
				c.sendString(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
