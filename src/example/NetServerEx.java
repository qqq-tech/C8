package example;

import net.ServerMultiThread;

public class NetServerEx {
	public static void main(String[] args) {
		ServerMultiThread smt = new ServerMultiThread();
		smt.openSocket(8888);
		smt.communicate();
	}
}
