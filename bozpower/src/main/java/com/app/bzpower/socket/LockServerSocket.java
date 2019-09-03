package com.app.bzpower.socket;

import java.io.IOException;
import java.net.ServerSocket;

public class LockServerSocket{
	
	private ServerSocket socket ;
	public LockServerSocket() throws IOException{
		
		socket = new ServerSocket();
		socket.accept();
		//创建一个线程，用于操作智能锁
	}
	
	
	
	/**
	 * 审核请求开锁
	 */
	@SuppressWarnings("unused")
	private void examineRequestOpenLockServer() {
		
	}
	
	

}
