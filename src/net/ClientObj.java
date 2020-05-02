package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientObj {
	private Socket socket = null;
	private ObjectOutputStream outputStream = null;

	private boolean isConnected = false;

	public ClientObj() {

	}

	public void finalize() throws IOException {
		if (outputStream != null)
			outputStream.close();
		if (socket != null)
			socket.close();
	}

	// ip:localhost and port
	public boolean connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println("Connected");
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			isConnected = true;
		} catch (SocketException se) {
			se.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return isConnected;
	}

	public boolean sendObject(Object obj) {
		boolean isSuccess = false;
		if (isConnected) {
			try {
				outputStream.writeObject(obj);
				isSuccess = true;
			} catch (SocketException se) {
				se.printStackTrace();
				// System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}