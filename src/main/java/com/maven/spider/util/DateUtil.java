package com.maven.spider.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final Date DATE = new Date();

	/**
	 * 获取年-月-日 时:分:秒格式的数据字符串
	 * @return
	 */
	public static String getYMDHMS() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
		String date = sdf.format(DATE);
		return date;
	}

}
