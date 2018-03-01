package com.maven.spider.gethtml;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetHtml {

	public void get(){
		// 创建HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建get请求
			HttpGet req = new HttpGet("http://www.xicidaili.com/nn/");
			req.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0");
			req.setHeader("charset", "UTF-8");
			req.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			// 发送get请求
			CloseableHttpResponse res = httpclient.execute(req);
			try{
				// 获取响应实体
				HttpEntity entity = res.getEntity();
				
				// 响应状态
				System.out.println(res.getStatusLine());
				if (entity != null){
					// 获取响应内容的长度
					long length = entity.getContentLength(); 
					// 获取响应的内容
					String content = EntityUtils.toString(entity,"UTF-8");
					
					System.out.println("内容长度：" + length + "\n具体内容：\n" + content);
				}
			}finally{
				res.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			// 关闭链接，释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main (String[] args){
		new GetHtml().get();
	}
}
