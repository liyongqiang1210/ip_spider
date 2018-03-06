package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.maven.spider.entity.IP;
import com.maven.spider.utils.DBUtils;
import com.maven.spider.utils.DateUtils;

public class JDBC {

	/**
	 * 添加ip方法
	 * 
	 * @param ip
	 */
	public void insertIP(IP ip) {

		Connection conn = DBUtils.getConnection();

		String sql = "INSERT INTO ip_list(id,ip_address,ip_prot,ip_type,ip_server_address,ip_is_user,ip_is_anonymous,create_time)"
				+ " values (?,?,?,?,?,?,?,?)";

		// 创建sql执行器对象
		PreparedStatement ps = null;// 预编译，减少sql运行

		// 创建查询结果集对象
		ResultSet rs = null;

		boolean execute = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, null);
			ps.setString(2, ip.getIp_address());
			ps.setString(3, ip.getIp_prot());
			ps.setString(4, ip.getIp_type());
			ps.setString(5, ip.getIp_server_address());
			ps.setInt(6, ip.getIp_is_user());
			ps.setString(7, ip.getIp_is_anonymous());
			ps.setString(8, DateUtils.getYMDHMS());

			// 查询数据库是否存在此条数据
			boolean ipIsExist = this.getIPIsExist(ip.getIp_address(), ip.getIp_prot());
			if (!ipIsExist) { // 如果不存在此条数据则执行插入操作
				// 执行插入操作
				execute = ps.execute();
				if (execute) {
					System.out.println(ip.getIp_address() + ":" + ip.getIp_prot() + "=========>此条ip信息插入成功！");
				} else {
					System.out.println(ip.getIp_address() + ":" + ip.getIp_prot() + "=========>此条ip信息插入失败！");
				}

			} else {
				System.out.println(ip.getIp_address() + ":" + ip.getIp_prot() + "=========>此条ip信息已经存在！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 关闭连接
			DBUtils.close(rs, ps, conn);
		}

	}

	/**
	 * 查询数据库是否存在此条ip地址
	 * 
	 * @param ip_address
	 * @return
	 */
	public boolean getIPIsExist(String ip_address, String ip_prot) {

		// sql语句
		String sql = "SELECT COUNT(id) FROM ip_list WHERE ip_address = ? AND ip_prot = ?";
		// 获取数据库连接
		Connection conn = DBUtils.getConnection();
		// 创建预编译对象
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ip_address);
			ps.setString(2, ip_prot);
			// 获取查询结果集
			ResultSet rs = ps.executeQuery();
			// 判断是否查询到数据
			if (rs.next()) { // 如果查询到数据那么返回true
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
