package com.app.bzpower.socket;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端
 * 
 * @author bozpower
 *
 */
public class LockClientSocket {
	private Socket socket;
	
	private String message;

	private byte[] b = { 1 };

	InputStream inputStream = null;
	InputStreamReader reader = null;
	OutputStream outputStream = null;
	OutputStreamWriter os = null;

	public LockClientSocket() {

	}

	public LockClientSocket(int port) {
		try {
			socket = new Socket("127.0.0.1", port);
			System.out.println("客户端与服务器建立连接！！！！");
			//connect();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("finally")
	public int connect() {

		try {
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
//			while (true) {
				System.out.println();
				outputStream.write(b);
				System.out.println("客户端发送数据C：" + Integer.valueOf(b[0]));
				Thread.sleep(5000);
				byte[] readByte = new byte[inputStream.read()];
				
				for (byte b : readByte) {
					System.out.println("客户端收到数据S：" + Integer.valueOf(b));
				}
				message = "hello world";
//			}
				return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return 0;
		}
	}
	
	public String getMessage() {
		return message;
	}

}
