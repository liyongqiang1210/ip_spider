package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.maven.spider.entity.Article;
import com.maven.spider.util.DBUtil;

/**
 * 技术博客url DB类
 * @author Li Yongqiang
 *
 */
public class ArticleJdbc {

	/**
	 * 添加博客url方法
	 * @param article
	 * @return
	 */
	public static boolean insertArticle(Article article){
		Connection conn = DBUtil.getConnection();
		String sql = "insert into article_url_list(url,title,author,create_time,source,article_type)values(?,?,?,?,?,?)";
		// 创建预编译对象
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, article.getUrl());
			ps.setString(2, article.getTitle());
			ps.setString(3, article.getAuthor());
			ps.setString(4, article.getCreateTime());
			ps.setString(5, article.getSource());
			ps.setString(6, article.getArticleType());
			
			int executeUpdate = ps.executeUpdate();
			if(executeUpdate == 1){
				System.out.println(article.getUrl()+"=========>添加成功！");
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(null, ps, conn);
		}
		return false;
	}
	
	/**
	 * 根据url查询是否存在此条记录
	 * @param url
	 * @return
	 */
	public static boolean selectArticle(String url){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from article_url_list where url = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, url);
			rs = ps.executeQuery();
			if(rs.next()){// 已存在 
				return false;
			}else{// 不存在
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return false;
	}
}
