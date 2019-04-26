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
	// 添加订单项
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
	// 修改订单项
	public boolean updateOrderitem(String orid,int meid, double num) {
		db=new DBUtil();
		String sql="update orderitem set num=num-? where orid=? and meid=?";
		try {
			int i = db.update(sql,num,orid,meid);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			db.closed();
		}
	}
	//删除订单项
	public boolean deleteOrderitem() {
		db=new DBUtil();
		String sql="delete from orderitem where num=0";
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

	//删除订单项(一个)
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
	// 通过orid查询订单项
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
	// 通过orid和meid找订单项
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
	
	//查询所有订单项
	public List<orderitem> selectAllitem(){
		List<orderitem> list=new ArrayList<orderitem>();
		db=new DBUtil();
		String sql="select * from orderitem";
		try {
			ResultSet res = db.qurey(sql);
			while(res.next()) {
				list.add(new orderitem(res.getString("orid"), res.getInt("meid"), res.getDouble("num")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			db.closed();
		}
	}
	
	//用meid进行分类查找每个菜的数量
	public List<orderitem> selectSumNum(){
		List<orderitem> list=new ArrayList<orderitem>();
		db=new DBUtil();
		String sql="select meid,sum(num) from orderitem group by meid order by sum(num) desc";
		try {
			ResultSet res = db.qurey(sql);
			while(res.next()) {
				list.add(new orderitem(res.getInt("meid"), res.getDouble("sum(num)")));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			db.closed();
		}
	}

}
