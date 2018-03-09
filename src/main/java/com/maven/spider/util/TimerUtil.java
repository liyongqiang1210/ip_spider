package com.maven.spider.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器工具类
 * @author Li Yongqiang
 *
 */
public class TimerUtil {

	// 创建定时器Timer对象
	private static final Timer timer = new Timer();
	
	public static void main(String[] args){
		
		// 启动后延迟时间
		long afterSs = 1*1000;
		// 执行周期
		long intervalSsl = 1*1000;
		timer.schedule(new TimerTask() {
			// 执行次数计数器
			int i = 0;
			@Override
			public void run() {
				System.out.println("当前时间：" + DateUtil.getYMDHMS() + "定时器执行次数：" + i++);
			}
		}, afterSs,intervalSsl);
	}
	
}
