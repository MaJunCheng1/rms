package com.ma.rms.biz;

import java.util.List;

import com.ma.rms.domain.employ;

public interface empBiz {
	//增加员工信息
	String insertEmp(employ e);
	//删除员工信息
	String deleteEmp(int id);
	//查看员工信息
	List<employ> showAllEmp();    
	//根据id查找员工信息
	public employ findById(int id);
	//根据账号密码查询客户信息
	public employ findOneEmp(String accout,String password);
}
