package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {
	//TODO. 재작업
	private Socket socket = null;
	private BufferedReader input = null;
	private OutputStream out = null;
	private InputStream in = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	private boolean isConnected = false;

	public Client() {

	}

	public void finalize() throws Exception {
		clearSession();
	}

	private void clearSession() throws Exception {
		isConnected = false;
		if (input != null) {
			input.close();
			input = null;
		}
		if (out != null) {
			out.close();
			out = null;
		}
		if (in != null) {
			in.close();
			in = null;
		}
		if (pw != null) {
			pw.close();
			pw = null;
		}
		if (br != null) {
			br.close();
			br = null;
		}
		if (socket != null) {
			socket.close();
			socket = null;
		}
	}

	// ip:localhost and port
	public boolean connect(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println("Connected");
			out = socket.getOutputStream(); // 서버의 소켓으로부터 출력을 받음
			in = socket.getInputStream(); // 서버의 소켓으로부터 입력을 받음
			pw = new PrintWriter(new OutputStreamWriter(out)); // 출력 스트림을 변환
			br = new BufferedReader(new InputStreamReader(in)); // 입력 스트림을 변환
			System.out.println("success open stream");
			isConnected = true;
		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isConnected;
	}

	public boolean sendString(String data) throws Exception {
		boolean isSuccess = false;
		if (isConnected) {
			try {
				// BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

				String myMsg = data; // 전달 메시지
				String echo = null; // 받는 메시지
				// while ((myMsg = input.readLine()) != null) {
				// if (myMsg.equals("/q")) {
				// break; // 연결 해제
				// }
				// }
				pw.println(myMsg); // PrintWriter를 이용하여 서버에게 전달
				pw.flush(); // 버퍼 비우기
				echo = br.readLine(); // 서버가 버퍼로 메시지를 전달하면 이를 읽음
				System.out.println("From Server: " + echo);
			} catch (Exception e) {
				e.printStackTrace();
				clearSession();
			}
		}
		return isSuccess;
	}
}