package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.maven.spider.entity.Csdn;
import com.maven.spider.util.DBUtil;

/**
 * csdnDB类
 * @author Li Yongqiang
 *
 */
public class CsdnJdbc {

	/**
	 * 添加csdn方法
	 * @param csdn
	 * @return
	 */
	public static boolean insertCsdn(Csdn csdn){
		Connection conn = DBUtil.getConnection();
		String sql = "insert into article_list(url,title,author,create_time,source,article_type)values(?,?,?,?,?,?)";
		// 创建预编译对象
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, csdn.getUrl());
			ps.setString(2, csdn.getTitle());
			ps.setString(3, csdn.getAuthor());
			ps.setString(4, csdn.getCreateTime());
			ps.setString(5, csdn.getSource());
			ps.setString(6, csdn.getArticleType());
			
			int executeUpdate = ps.executeUpdate();
			if(executeUpdate == 1){
				System.out.println(csdn.getUrl()+"=========>添加成功！");
				return true;
			}
			System.out.println(csdn.getUrl()+"=========>添加失败！");
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(null, ps, conn);
		}
		return false;
	}
	
	/**
	 * 根据url查询csdn是否存在
	 * @param url
	 * @return
	 */
	public static boolean selectCsdn(String url){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from article_list where url = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, url);
			rs = ps.executeQuery();
			if(rs.next()){// 已存在 
				System.out.println(url + "===========>已存在，添加失败!");
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
