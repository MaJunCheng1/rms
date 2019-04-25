package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ma.rms.dao.menuDao;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.vegetType;
import com.ma.rms.util.DBUtil;

public class menuDaoImpl implements menuDao{
	private DBUtil db;
	//根据meid查找该菜的信息
	public menu findFoodById(int meid) {
		db=new DBUtil();
		String sql="select * from menu where meid="+meid;
		try {
			ResultSet res = db.qurey(sql);
			if(res.next()) {
				return new menu(res.getInt("meid"), res.getString("mename"), res.getDouble("meprice"), res.getInt("typeid"), res.getString("ifspecials"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			db.closed();
		}
		return null;
	}
	//查找所有菜的信息
	public List<menu> findAllFood() {
		List<menu> list=new ArrayList<menu>();
		db=new DBUtil();
		String sql="select * from menu order by meid";
		try {
			ResultSet res = db.qurey(sql);
			while(res.next()) {
				list.add(new menu(res.getInt("meid"), res.getString("mename"), res.getDouble("meprice"), res.getInt("typeid"), res.getString("ifspecials")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			db.closed();
		}
	}
	//增加菜品信息
	public boolean insertFood(menu me) {
		db=new DBUtil();
		String sql="insert into menu values(?,?,?,?,?)";
		try {
			int i = db.update(sql,me.getMeid(),me.getMename(),me.getMeprice(),me.getTypeid(),me.getIfspecials());
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}
	//删除菜品
	public boolean deleteFood(int meid) {
		db=new DBUtil();
		String sql="delete from menu where meid="+meid;
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
	//修改菜品信息
	public boolean updateFood(int meid,String mename,double meprice,int typeid,String ifspecials) {
		db=new DBUtil();
		String sql="update menu set mename=?,meprice=?,typeid=?,ifspecials=? where meid="+meid;
		try {
			int i = db.update(sql, mename,meprice,typeid,ifspecials);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}
	//通过typeid查找菜品信息
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
			System.out.println(e.getMessage());
			return null;
		}finally {
			db.closed();
		}
	}
	//通过typeid查找typename
	public String findtypename(int typeid) {
		db=new DBUtil();
		String sql="select typename from vegetype where typeid="+typeid;
		try {
			ResultSet res = db.qurey(sql);
			if(res.next()) {
				return  res.getString("typename");
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			db.closed();
		}
	}
	
	//选择特价菜
	public boolean selectSpecial(String s,int id) {
		db=new DBUtil();
		String sql="update menu set ifspecials=? where meid="+id;
		try {
			int j = db.update(sql,s);
			return j>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}
	//修改价格
	public boolean setSpecial(double price,int id) {
		db=new DBUtil();
		String sql2="update menu set meprice=? where meid="+id;
		try {
				int i = db.update(sql2,price);
				return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}
	//显示所有菜的类型
	public List<vegetType> showVegeType() {
		db=new DBUtil();
		String sql="select * from vegetype";
		List<vegetType> list=new ArrayList<vegetType>();
		try {
			ResultSet rs = db.qurey(sql);
			while(rs.next()) {
				list.add(new vegetType(rs.getInt("typeid"),rs.getString("typename")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			this.db.closed();
		}
		return null;
	}
}
