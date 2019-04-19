package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ma.rms.dao.menuDao;
import com.ma.rms.domain.menu;
import com.ma.rms.util.DBUtil;

public class menuDaoImpl implements menuDao{
	private DBUtil db;
	
	public menu findFoodById(int meid) {
		db=new DBUtil();
		String sql="select * from menu where meid="+meid;
		try {
			ResultSet res = db.qurey(sql);
			if(res.next()) {
				return new menu(res.getInt("meid"), res.getString("mename"), res.getDouble("meprice"), res.getInt("typeid"), res.getString("ifspecials"));
			}
		} catch (SQLException e) {
			System.out.println("出现的错误是:"+e.getMessage());
			return null;
		}finally {
			db.closed();
		}
		return null;
	}

	public List<menu> findAllFood() {
		List<menu> list=new ArrayList<menu>();
		db=new DBUtil();
		String sql="select * from menu";
		try {
			ResultSet res = db.qurey(sql);
			while(res.next()) {
				list.add(new menu(res.getInt("meid"), res.getString("mename"), res.getDouble("meprice"), res.getInt("typeid"), res.getString("ifspecials")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("出现的错误是:"+e.getMessage());
			return null;
		}finally {
			db.closed();
		}
	}

	public boolean insertFood(menu me) {
		db=new DBUtil();
		String sql="insert into menu values(?,?,?,?,?)";
		try {
			int i = db.update(sql,me.getMeid(),me.getMename(),me.getMeprice(),me.getTypeid(),me.getIfspecials());
			return i>0;
		} catch (SQLException e) {
			System.out.println("出现的错误是:"+e.getMessage());
			return false;
		}
	}

	public boolean deleteFood(int meid) {
		db=new DBUtil();
		String sql="delete from menu where meid="+meid;
		try {
			int i = db.update(sql);
			return i>0;
		} catch (SQLException e) {
			System.out.println("出现的错误是:"+e.getMessage());
			return false;
		}
	}

	public boolean updateFood(int meid,String mename,double meprice,int typeid,String ifspecials) {
		db=new DBUtil();
		String sql="update menu set mename=?,meprice=?,typeid=?,ifspecials=? where meid="+meid;
		try {
			int i = db.update(sql, mename,meprice,typeid,ifspecials);
			return i>0;
		} catch (SQLException e) {
			System.out.println("出现的错误是:"+e.getMessage());
			return false;
		}
	}

	public List<menu> findFood(int typeid) {
		List<menu> list=new ArrayList<menu>();
		db=new DBUtil();
		String sql="select * from menu where typeid="+typeid;
		try {
			ResultSet res = db.qurey(sql);
			while(res.next()) {
				list.add(new menu(res.getInt("meid"), res.getString("mename"), res.getDouble("meprice"), res.getInt("typeid"), res.getString("ifspecials")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
