package com.wpf.MyFirstMaven;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description 服务端
 * @author perfecttomcat
 * @date 2021年7月4日 上午09:57:03
 */
public class HttpServerInstance {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8801);

		while (true) {
			Socket listenerSocket = serverSocket.accept();
			provideService(listenerSocket);
		}
	}

	private static boolean provideService(Socket listenerSocket) {
		try {
			PrintWriter printWriter = new PrintWriter(listenerSocket.getOutputStream());
			printWriter.println("HTTP/1.1 200 OK");
			printWriter.println("Content-Type:text/html;charset=utf-8");
			String returnBody = "hello nio";
			printWriter.println("Content-Length:" + returnBody.getBytes().length);
			printWriter.println();
			printWriter.write(returnBody);
			printWriter.close();
			listenerSocket.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
