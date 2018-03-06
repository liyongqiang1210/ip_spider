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

			// 执行插入操作
			execute = ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 判断是否插入成功
			if (execute) {
				System.out.println("==========>插入成功！");
			} else {
				System.out.println("==========>插入失败！");
			}
			// 关闭连接
			DBUtils.close(rs, ps, conn);
		}

	}

}
