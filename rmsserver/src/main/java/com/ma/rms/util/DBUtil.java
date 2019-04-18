package com.ma.rms.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	public DBUtil() {
		Properties pro=new Properties();
		try {
			pro.load(new FileInputStream("prop.properties"));
			Class.forName(pro.getProperty("classname")).newInstance();
			conn=DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("username"), pro.getProperty("password"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public int update(String sql) throws SQLException{
		//获取数据库操作对象
		stmt=conn.createStatement();
		return stmt.executeUpdate(sql);
	}
	public int update(String sql,Object...arr) throws SQLException{
		pstmt=conn.prepareStatement(sql);
		for (int i = 0; i < arr.length; i++) {
			pstmt.setObject((i+1), arr[i]);
		}
		return pstmt.executeUpdate();
	}
	public ResultSet qurey(String sql) throws SQLException{
		stmt=conn.createStatement();
		return stmt.executeQuery(sql);
	}
	public ResultSet qurey(String sql,Object...arr) throws SQLException{
		pstmt=conn.prepareStatement(sql);
		for (int i = 0; i < arr.length; i++) {
			pstmt.setObject((i+1),arr[i]);
		}
		return pstmt.executeQuery();
	}
	public void closed(){
			try {
				if(pstmt!=null &&!pstmt.isClosed())pstmt.close();
				if(stmt!=null &&!stmt.isClosed())stmt.close();
				if(conn!=null &&!conn.isClosed())conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
