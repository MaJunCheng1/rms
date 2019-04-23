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
			System.out.println(e.getMessage());
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
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}

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
	
	
	public boolean selectSpecial(int id) {
		db=new DBUtil();
//		String sql="update set ifspecials='否'";
		String sql2="update menu set ifspecials='是' where meid="+id;
		try {
//			int i = db.update(sql);
			int j = db.update(sql2);
			return j>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}

	public boolean setSpecial(int id) {
		db=new DBUtil();
		String sql="select * from menu where meid="+id;
		String sql2="update menu set meprice=? where meid="+id;
		try {
			ResultSet rs = db.qurey(sql);
			if(rs.next()){
				int i = db.update(sql2,rs.getDouble("meprice")*0.3);
				return i>0;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
		return false;
	}

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
