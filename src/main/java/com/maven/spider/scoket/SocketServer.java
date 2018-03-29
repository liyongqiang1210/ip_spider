package com.maven.spider.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {

		try {
			ServerSocket server = new ServerSocket(54278);
			System.out.println("服务器已经启动！");
			// 接受客户端发送得到消息
			Socket socket = server.accept();
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = br.readLine();
			if (info != null) {
				System.out.println(info);
				System.out.println("客户端ip为：" + socket.getRemoteSocketAddress());
			}
			
			// 向客户端写信息
			OutputStream os = socket.getOutputStream();
			String str = "欢迎登陆服务器！！";
			os.write(str.getBytes());
			
			// 关闭文件流
			os.close();
			br.close();
			is.close();
			socket.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
