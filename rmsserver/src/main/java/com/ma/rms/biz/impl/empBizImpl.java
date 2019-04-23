package com.ma.rms.biz.impl;

import java.util.List;

import com.ma.rms.biz.empBiz;
import com.ma.rms.dao.empDao;
import com.ma.rms.dao.impl.empDaoImpl;
import com.ma.rms.domain.employ;

public class empBizImpl implements empBiz {
	empDao ed;
	
	public empBizImpl() {
		super();
		ed=new empDaoImpl();
	}
	public String insertEmp() {
		return this.ed.insertEmp(new employ())?"添加成功":"添加失败";
	}
	public String deleteEmp(int id) {
		return this.ed.deleteEmp(id)?"删除成功":"删除失败";
	}

	public List<employ> showAllEmp() {
		List<employ> list = this.ed.findAllEmp();
		return list;
	}
	public employ findById(int id) {
		return this.ed.findById(id);
	}
	public employ findOneEmp(String accout, String password) {
		return ed.findOneEmp(accout, password);
	}
	
}
