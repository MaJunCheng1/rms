package com.ma.rms.biz.impl;

import java.util.List;

import org.junit.Test;

import com.ma.rms.biz.empBiz;
import com.ma.rms.dao.empDao;
import com.ma.rms.dao.impl.empDaoImpl;
import com.ma.rms.domain.employ;

public class empBizImp implements empBiz{
	empDao ed=new empDaoImpl();
	public String insertEmp() {
		return this.ed.insertEmp(new employ())?"添加成功":"添加失败";
	}
	@Test
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
	
}
