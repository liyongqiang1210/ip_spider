package com.maven.spider.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 获取年-月-日 时:分:秒格式的数据字符串
	 * @return
	 */
	public static String getYMDHMS() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		return date;
	}

}
