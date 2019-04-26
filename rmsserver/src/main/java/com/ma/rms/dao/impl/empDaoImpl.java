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
	// 添加员工信息
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
	// 删除员工信息
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
	// 查找所有员工信息
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
	// 根据账号密码查询客户信息
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
	// 根据id查找员工
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

	//修改密码
	public boolean updatePass(int eid,String newPass) {
		db=new DBUtil();
		String sql="update emp set password=? where eid=?";
		try {
			int i = this.db.update(sql,newPass,eid);
			return i>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			db.closed();
		}
	}
	public employ selectEmpByAccount(String account) {
		db=new DBUtil();
		String sql="select * from emp where accout="+account;
		try {
			ResultSet rs = db.qurey(sql);
			if(rs.next()) {
				return new employ(rs.getInt("eid"),rs.getString("ename"),rs.getString("accout"),rs.getString("password"),rs.getString("jobtype"),rs.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
