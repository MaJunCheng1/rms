package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.ma.rms.dao.ordersDao;
import com.ma.rms.domain.orders;
import com.ma.rms.util.DBUtil;

public class ordersDaoImpl implements ordersDao{
	private DBUtil db;
	public boolean insertOrders(orders o) {
		db=new DBUtil();
		String sql="insert into orders values(?,?,?,?,?)";
		try {
			ResultSet rs = db.qurey(sql, o.getOrid(),new java.sql.Date(o.getOrtime().getTime()),o.getEid(),o.getCarid(),o.getLocid());
			return rs.next();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}
	public orders selectOrdersById(String orid) {
		db=new DBUtil();
		String sql="select * from orders where orid=?";
		try {
			ResultSet res = db.qurey(sql,orid);
			if(res.next()) {
			return new orders(res.getString("orid"),new Date(res.getDate("ortime").getTime()) , res.getInt("eid"), res.getInt("carid"), res.getInt("locid"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			this.db.closed();
		}
		return null;
	}
	public boolean deleteOrders(String orid) {
		db=new DBUtil();
		String sql="delete from orders where orid=?";
		try {
			int i = db.update(sql, orid);
			return i>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			db.closed();
		}
	}

}
