package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerMultiThread {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private boolean isOpened = false;
	private boolean isStop = false;

	public ServerMultiThread() {

	}

	public void finalize() throws IOException {
		if (serverSocket != null)
			serverSocket.close();
		if (socket != null)
			socket.close();
	}

	public boolean openSocket(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(getTime() + " 서버가 준비되었습니다.");
			isOpened = true;
		} catch (IOException e) {
			e.printStackTrace();
		} // 포트에 서버소켓을 붙인다(Bind)

		return isOpened;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public void communicate() {
		try {

			while (!isStop) {
				Socket socket = serverSocket.accept(); // 클라이언트의 접속을 허가한다.(Accept)
				InetAddress clientAddress = socket.getInetAddress(); // 클라이언트의 주소를 가져온다.
				System.out.println(getTime() + clientAddress + " 에서 클라이언트가 접속했습니다.");

				Thread t = new ServerThread(socket);
				ResultInterface.setThreadID(t.getName());
				t.start();
			}

		} catch (Exception e) {
			e.printStackTrace(); // 예외 처리
		}
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // 날짜 출력
		return f.format(new Date());
	}

}
