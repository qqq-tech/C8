package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerObj {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ObjectInputStream inStream = null;

	public ServerObj() {

	}

	public void finalize() throws IOException {
		if (inStream != null)
			inStream.close();
		if (serverSocket != null)
			serverSocket.close();
		if (socket != null)
			socket.close();
	}

	public void communicate() {
		try {
			serverSocket = new ServerSocket(4445);
			socket = serverSocket.accept();
			System.out.println("Connected");
			inStream = new ObjectInputStream(socket.getInputStream());
			Object obj= inStream.readObject();
			socket.close();
		} catch (SocketException se) {
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		}
	}
 }
