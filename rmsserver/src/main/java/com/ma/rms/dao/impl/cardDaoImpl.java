package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ma.rms.dao.cardDao;
import com.ma.rms.domain.card;
import com.ma.rms.util.DBUtil;

public class cardDaoImpl implements cardDao {
	private DBUtil db;
	public boolean insertCard(card c) {
		db=new DBUtil();
		String sql="insert into card values(?,?,?,?,?)";
		try {
			int i = db.update(sql,c.getCarid(),c.getEid(),c.getPayid(),c.getBalance(),c.getStatus());
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}
	public boolean updateBalance(int carid, double balance) {
		db=new DBUtil();
		String sql="update card set balance=balance+? where carid=?";
		try {
			int i = db.update(sql, balance,carid);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}

	public boolean updateStatus(int carid, String status) {
		db=new DBUtil();
		String sql="update card set status=? where carid=?";
		try {
			int i = db.update(sql, status,carid);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}
	
	public card selectCardById(int carid) {
		db=new DBUtil();
		String sql="select * from card where carid="+carid;
		try {
			ResultSet res = db.qurey(sql);
			if(res.next()) {
				return new card(res.getInt("carid"), res.getInt("eid"), res.getInt("payid"), res.getDouble("balance"), res.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			db.closed();
		}
		return null;
	}

}
