package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		int execute = 0;
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
				execute = ps.executeUpdate();
				if (execute == 1) {
					System.out.println(ip.getIp_address() + ":" + ip.getIp_prot() + "=========>此条ip信息插入成功！");
				} else {
					System.out.println(ip.getIp_address() + ":" + ip.getIp_prot() + "=========>此条ip信息插入失败！");
				}

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
		String sql = "SELECT * FROM ip_list WHERE ip_address = ? AND ip_prot = ?";
		// 获取数据库连接
		Connection conn = DBUtils.getConnection();
		// 创建预编译对象
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ip_address);
			ps.setString(2, ip_prot);
			System.out.println("执行的sql: " + ps.toString());
			// 获取查询结果集
			ResultSet rs = ps.executeQuery();
			// 判断是否查询到数据
			if (rs.next()) { // 如果查询到数据那么返回true
				System.out.println(ip_address + ":" + ip_prot + "=========>此条信息已经存在！");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取可用的ip列表
	 * 
	 * @return
	 */
	public List<IP> getIPList() {
		List<IP> list = new ArrayList<IP>();
		String sql = "SELECT * FORM ip_list WHERE ip_is_user = 1 limit 20";
		Connection conn = DBUtils.getConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			IP ip = null;
			while(rs.next()){
				ip = new IP();
				ip.setIp_address(rs.getString("ip_address"));
				ip.setIp_prot(rs.getString("ip_prot"));
				ip.setIp_is_anonymous(rs.getString("ip_is_anonymous"));
				ip.setIp_is_user(rs.getInt("ip_is_user"));
				ip.setIp_server_address(rs.getString("ip_server_address"));
				ip.setIp_type(rs.getString("ip_type"));
				ip.setCreate_time(rs.getString("create_time"));
				list.add(ip);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
