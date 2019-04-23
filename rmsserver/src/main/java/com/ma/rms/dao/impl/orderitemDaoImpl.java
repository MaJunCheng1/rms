package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ma.rms.dao.orderitemDao;
import com.ma.rms.domain.orderitem;
import com.ma.rms.util.DBUtil;

public class orderitemDaoImpl implements orderitemDao {
	private DBUtil db;
	
	public boolean addOrderitem(orderitem oi) {
		db=new DBUtil();
		String sql="insert into orderitem values(?,?,?)";
		try {
			int i = db.update(sql, oi.getOrid(),oi.getMeid(),oi.getNum());
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}

	public boolean updateOrderitem(String orid, double num) {
		db=new DBUtil();
		String sql="update orderitem set num=num-"+num;
		try {
			int i = db.update(sql);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}

	public boolean deleteOrderitem(String orid) {
		db=new DBUtil();
		String sql="delete from orderitem where orid=?";
		try {
			int i = db.update(sql,orid);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}

	
	public boolean deleteOneOrderitem(String orid, int meid) {
		db=new DBUtil();
		String sql="delete from orderitem where orid=? and meid=?";
		try {
			int i = db.update(sql, orid,meid);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}

	public List<orderitem> selectitem(String orid) {
		List<orderitem> list=new ArrayList<orderitem>();
		db=new DBUtil();
		String sql="select * from orderitem where orid=?";
		try {
			ResultSet res = db.qurey(sql,orid);
			while(res.next()) {
				list.add(new orderitem(res.getString("orid"), res.getInt("meid"), res.getDouble("num")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			db.closed();
		}
	}

	public orderitem selectOrderByoridandmeid(String orid, int meid) {
		db=new DBUtil();
		String sql="select * from orderitem where orid=? and meid=?";
		try {
			ResultSet res = db.qurey(sql, orid,meid);
			if(res.next()) {
				return new orderitem(res.getString("orid"), res.getInt("meid"), res.getDouble("num"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			db.closed();
		}
		return null;
		
	}
	
	

}
