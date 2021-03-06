package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maven.spider.entity.UrlList;
import com.maven.spider.util.DBUtil;

public class NewsUrlJdbc {

	/**
	 * 获取爬取网址的方法
	 * @param urlType
	 * @return
	 */
	public static List<UrlList> getUrlList(String newsType) {

		List<UrlList> list = new ArrayList<UrlList>();
		UrlList ut = new UrlList();
		String sql = "select * from url_list where news_url_source = ?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newsType);
			rs = ps.executeQuery();
			while (rs.next()) {
				ut.setNewsUrlSource(rs.getString(2));
				ut.setNewsUrl(rs.getString(3));
				ut.setNewsType(rs.getString(4));
				list.add(ut);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, ps, conn);
		}

		return list;

	}
}
