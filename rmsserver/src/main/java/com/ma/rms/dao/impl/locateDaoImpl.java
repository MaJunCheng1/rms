package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ma.rms.dao.locateDao;
import com.ma.rms.domain.locate;
import com.ma.rms.util.DBUtil;

public class locateDaoImpl implements locateDao {
	private DBUtil db;
	//通过locid获取地址
	public locate findLocate(int id) {
		db=new DBUtil();
		String sql="select * from locate where locid="+id;
		try {
			ResultSet rs = db.qurey(sql);
			if(rs.next()){
				return new locate(id,rs.getString("locname"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			db.closed();
		}
		return null;
	}

}
