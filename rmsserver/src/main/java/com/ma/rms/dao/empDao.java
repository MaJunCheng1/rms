package com.ma.rms.dao;

import java.util.List;

import com.ma.rms.domain.employ;

public interface empDao {
	//添加员工信息
		public boolean insertEmp(employ e);
		//删除员工信息
		public boolean deleteEmp(int id);
		//查找所有员工信息
		public List<employ> findAllEmp();
		//根据账号密码查询客户信息
		public employ findOneEmp(String accout,String password);
		//根据id查找员工
		public employ findById(int id);
}
