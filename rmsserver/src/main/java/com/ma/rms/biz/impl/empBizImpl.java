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
	public String insertEmp(employ e) {
		return this.ed.insertEmp(e)?"添加成功":"添加失败";
	}
	public String deleteEmp(int id) {
		return this.ed.deleteEmp(id)?"删除成功":"没有该员工";
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
	//修改密码
	public String changePass(int eid,String newPass) {
		return this.ed.updatePass(eid,newPass)?"修改成功":"修改失败";
	}
	//根据账号找员工
	public employ findEmpByAccount(String account) {
		return ed.selectEmpByAccount(account);
		
	}
}
