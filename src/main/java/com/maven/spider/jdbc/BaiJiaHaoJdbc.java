package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.maven.spider.entity.BaiJiaHao;
import com.maven.spider.util.DBUtil;

public class BaiJiaHaoJdbc {

	/**
	 * 此方法是根据传入的url参数判断数据库是否存在此条数据
	 * 
	 * @param url
	 * @return
	 */
	public static boolean selectBaiJiaHaoIsExist(String url) {

		String sql = "select * from baijiahao_url_list where url = ?";
		Connection conn = DBUtil.getConnection(); // 获取数据库连接
		PreparedStatement ps = null; // 创建sql预编译对象
		ResultSet rs = null; // 返回结果集对象
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, url);
			rs = ps.executeQuery();
			if (rs.next()) { // 存在
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 关闭连接
			DBUtil.close(rs, ps, conn);
		}
		return false;
	}

	/**
	 * 添加百家号文章的方法需传入百家号文章对象
	 * 
	 * @param baijiahao
	 */
	public static void insertBaiJiaHao(BaiJiaHao baiJiaHao) {
		String sql = "insert into baijiahao_url_list values(?,?,?,?,?,?,?,?,0)";
		Connection conn = DBUtil.getConnection(); // 获取数据库连接
		PreparedStatement ps = null; // 创建sql预编译对象
		int state = 0; 
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, baiJiaHao.getId());
			ps.setString(2, baiJiaHao.getUrl());
			ps.setString(3, baiJiaHao.getTitle());
			ps.setString(4, baiJiaHao.getAuthor());
			ps.setString(5, baiJiaHao.getAuthorUrl());
			ps.setString(6, baiJiaHao.getReleaseTime());
			ps.setString(7, baiJiaHao.getType());
			ps.setString(8, baiJiaHao.getImageUrl());
			state = ps.executeUpdate();
			if(state == 1) {// 添加成功
				System.out.println(baiJiaHao.getTitle() + "========>添加成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 关闭连接
			DBUtil.close(null, ps, conn);
		}
	}
}
