package com.maven.spider.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.maven.spider.entity.IP;


public class DBUtils {

	public static final String URL = "jdbc:mysql://localhost:3306/maven";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	private static Connection conn = null;
	
	// 获取当前日期
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
	String create_time = sdf.format(date);
	
	static {
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获得数据库连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 获取连接
	public static Connection getConnection() {
		return conn;
	}

	// 关闭连接的方法
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		// 关闭数据库连接
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加ip方法
	 * 
	 * @param ip
	 */
	public void insertIP(IP ip) {

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
			ps.setString(8, create_time);

			// 执行插入操作
			execute = ps.execute();
			System.out.println("执行的sql:" + sql);
			if(execute){
				System.out.println("==========>插入成功！");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			DBUtils.close(rs, ps, conn);
		}
		
	}
	
//	public static void main(String[] args) {
//		IP ip = new IP();
//		ip.setIp_type("http");
//		ip.setIp_address("120.0.0.1");
//		ip.setIp_is_anonymous("高匿");
//		ip.setIp_is_user(0);
//		ip.setIp_prot("80");
//		ip.setIp_server_address("河北");
//		new DBUtils().insertIP(ip);
//	}
}
