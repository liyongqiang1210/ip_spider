package com.maven.spider.main;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.maven.spider.entity.Csdn;
import com.maven.spider.httpclient.HttpClientRequest;
import com.maven.spider.jdbc.CsdnJdbc;
import com.maven.spider.parser.CsdnParser;
import com.maven.spider.util.DateUtil;

public class CsdnMain {

	// 创建定时器Timer对象
	private static final Timer timer = new Timer();
	// 创建模拟浏览器请求对象
	private final static HttpClientRequest hcq = new HttpClientRequest();

	public static void main(String[] args) {
		// 启动后延迟时间
		long afterSs = 1 * 1000;
		// 执行周期
		long intervalSsl = 1 * 30000;
		timer.schedule(new TimerTask() {
			// 执行次数计数器
			int i = 0;

			@Override
			public void run() {
				System.out.println("当前时间：" + DateUtil.getYMDHMS() + "定时器执行次数：" + i++);
				String content = hcq.get("https://www.csdn.net/nav/newarticles");
				List<Csdn> csdnArcitleList = CsdnParser.getCsdnArcitleList(content, "最新文章");
				for (Csdn csdn : csdnArcitleList) {
					String url = csdn.getUrl();
					if(url != null && url != "") {
						boolean selectCsdn = CsdnJdbc.selectCsdn(url);
						if (selectCsdn) {
							CsdnJdbc.insertCsdn(csdn);
						}
					}
				}
			}
		}, afterSs, intervalSsl);
	}
}
