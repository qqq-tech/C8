package net;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private Socket socket;
	private InputStream input = null;
	private BufferedReader reader = null;
	private OutputStream output = null;
	private PrintWriter writer = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void finalize() throws IOException {
		if (reader != null) {
			reader.close();
			reader = null;
		}
		if (input != null) {
			input.close();
			input = null;
		}
		if (writer != null) {
			writer.close();
			writer = null;
		}
		if (output != null) {
			output.close();
			output = null;
		}

		if (socket != null) {
			socket.close();
			socket = null;
		}
	}

	public void run() {
		try {
			input = socket.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input));
			output = socket.getOutputStream();
			writer = new PrintWriter(output, true);
			String text;
			do {
				text = reader.readLine();
				String reverseText = new StringBuilder(text).reverse().toString();
				writer.println("Server: " + reverseText);

				ResultInterface.setResult(this.getName(), reverseText);
			} while (!text.equals("bye"));

		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}