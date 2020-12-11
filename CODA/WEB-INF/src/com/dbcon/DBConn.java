package com.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
	public static String SELECT = "SELECT";
	public static String UPDATE = "UPDATE";

	public static ResultSet performDBOperation(String sql, String query) {
		try {
			Connection con = DriverManager.getConnection("postgres://aprctugcufuqyt:846ff8393741cf17febbd285f6fef0425f4a2c7a5cd47422708c205052c6ab1c@ec2-52-201-184-16.compute-1.amazonaws.com:5432/dece4qhds03dgq", "aprctugcufuqyt", "846ff8393741cf17febbd285f6fef0425f4a2c7a5cd47422708c205052c6ab1c");
			Statement stmt = con.createStatement();
			if (query.equals(DBConn.SELECT)) {
				ResultSet rs = stmt.executeQuery(sql);
				return rs;
			} else if (query.equals(DBConn.UPDATE)) {
				stmt.executeUpdate(sql);
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
