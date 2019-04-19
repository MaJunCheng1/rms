package com.ma.rms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ma.rms.dao.empDao;
import com.ma.rms.domain.employ;
import com.ma.rms.util.DBUtil;

public class empDaoImpl implements empDao {
	private DBUtil db;
	public boolean insertEmp(employ e) {
		db=new DBUtil();
		String sql="insert into emp values(?,?,?,?,?,?)";
		try {
			int i = db.update(sql, e.getEid(),e.getEname(),e.getAccout(),e.getPassword(),e.getJobtype(),e.getSex());
			return i>0;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}

	public boolean deleteEmp(int id) {
		db=new DBUtil();
		String sql="delete from emp where eid="+id;
		try {
			int i = db.update(sql);
			return i>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}finally{
			this.db.closed();
		}
	}

	public List<employ> findAllEmp() {
		db=new DBUtil();
		String sql="select * from emp";
		List<employ> list=new ArrayList<employ>();
		try {
			ResultSet rs = db.qurey(sql);
			while(rs.next()){
				list.add(new employ(rs.getInt("eid"),rs.getString("ename"),rs.getString("accout"),rs.getString("password"),rs.getString("jobtype"),rs.getString("sex")));
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			this.db.closed();
		}
	}

	public employ findOneEmp(String accout, String password) {
		db=new DBUtil();
		String sql="select * from emp where accout=? and password=?";
		try {
			ResultSet rs = db.qurey(sql,accout,password);
			if(rs.next()){
				return new employ(rs.getInt("eid"),rs.getString("ename"),rs.getString("accout"),rs.getString("password"),rs.getString("jobtype"),rs.getString("sex"));
			}	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			this.db.closed();
		}
		return null;
	}

	public employ findById(int id) {
		db=new DBUtil();
		String sql="select * from emp where eid="+id;
		try {
			ResultSet rs = this.db.qurey(sql);
			if(rs.next()){
				return new employ(rs.getInt("eid"),rs.getString("ename"),rs.getString("accout"),rs.getString("password"),rs.getString("jobtype"),rs.getString("sex"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
