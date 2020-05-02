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

public class Server {
	//TODO. 재작업
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private boolean isOpened = false;

	public Server() {

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

	public void communicate() {
		try {
			Socket socket = serverSocket.accept(); // 클라이언트의 접속을 허가한다.(Accept)
			InetAddress clientAddress = socket.getInetAddress(); // 클라이언트의 주소를 가져온다.
			System.out.println(getTime() + clientAddress + " 에서 클라이언트가 접속했습니다.");
			OutputStream out = socket.getOutputStream(); // 클라이언트 소켓의 바이트 스트림을 가져온다.
			InputStream in = socket.getInputStream(); // 클라이언트 소켓의 바이트 스트림을 입력한다.

			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String recvStr = null; // 받은 문자열
			while ((recvStr = br.readLine()) != null) {
				System.out.println(getTime() + " 클라이언트로부터 받은 문자열 : " + recvStr);
				pw.println(recvStr); // 메시지를 클라이언트에게 전송
				pw.flush(); // 버퍼를 비움
			}

			pw.close(); // 스트림 닫기
			br.close(); // 버퍼 닫기
			socket.close(); // 소켓 닫기
		} catch (Exception e) {
			e.printStackTrace(); // 예외 처리
		}
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]"); // 날짜 출력
		return f.format(new Date());
	}

}
