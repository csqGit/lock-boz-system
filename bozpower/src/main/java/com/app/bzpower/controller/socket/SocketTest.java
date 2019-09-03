package com.app.bzpower.controller.socket;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.bzpower.socket.LockClientSocket;


@RestController
public class SocketTest {
	
	@RequestMapping(value = "openLock")
	public String openLock() throws IOException {
		System.out.println("请求开锁");
//		LockServerSocket lockServerSocket = new LockServerSocket();
		//与服务器建立连接
		LockClientSocket client = new LockClientSocket(8084);
		//业务处理   返回结果
		int result = client.connect();
		//
		if(result == 1) {
			System.out.println("开锁成功！");
		}else {
			System.out.println("开锁失败！");
		}
		return "success";
	}

}
