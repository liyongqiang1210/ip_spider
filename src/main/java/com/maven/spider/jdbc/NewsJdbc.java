package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.maven.spider.entity.News;
import com.maven.spider.util.DBUtil;

/**
 * 新闻url DB类
 * 
 * @author Li Yongqiang
 *
 */
public class NewsJdbc {

	/**
	 * 判断这条新闻url是否存在的方法
	 * 
	 * @param newsUrl
	 * @return
	 */
	public static boolean getNewsUrlIsExit(String newsUrl) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from news_list where news_url = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newsUrl);
			rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return false;
	}

	public static void insertNewsUrl(News news) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into news_list(news_id,news_title,news_url,news_source_website,news_type,news_release_time) "
				+ "values(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, news.getNewsId());
			ps.setString(2, news.getNewsTitle());
			ps.setString(3, news.getNewsUrl());
			ps.setString(4, news.getNewsSourceWebsite());
			ps.setString(5, news.getNewsType());
			ps.setString(6, news.getNewsReleaseTime());
			System.out.println(ps.toString());
			int isSuccess = ps.executeUpdate();
			if(isSuccess == 1){
				System.out.println(news.getNewsUrl() + "================>添加成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, ps, conn);
		}
	}
}
