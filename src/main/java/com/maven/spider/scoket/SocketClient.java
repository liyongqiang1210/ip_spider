package com.maven.spider.scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args){
		
		System.out.println("客户端已经启动！");
		try {
			Socket socket = new Socket("localhost",54278);
			OutputStream os = socket.getOutputStream();
			String msg = "我要登陆服务器！";
			os.write(msg.getBytes());
			socket.shutdownOutput();
			
			// 获取服务器响应的信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = br.readLine();
			if(info != null){
				System.out.println(info);
			}
			
			// 关闭连接
			br.close();
			is.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
