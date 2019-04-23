package com.ma.rms.service.impl;


import java.util.List;

import com.ma.rms.biz.cardBiz;
import com.ma.rms.biz.empBiz;
import com.ma.rms.biz.menuBiz;
import com.ma.rms.biz.impl.cardBizImpl;
import com.ma.rms.biz.impl.empBizImpl;
import com.ma.rms.biz.impl.menuBizIpml;
import com.ma.rms.domain.card;
import com.ma.rms.domain.employ;
import com.ma.rms.domain.menu;
import com.ma.rms.service.BackstageService;

public class BackstageServiceImpl implements BackstageService {
	private empBiz eb;
	private cardBiz cb;
	private menuBiz mb;
	public BackstageServiceImpl() {
		super();
		this.eb = new empBizImpl();
		this.cb = new cardBizImpl();
		this.mb = new menuBizIpml();
	}

	public String addEmp(employ e) {
		return this.eb.insertEmp();
	}

	public String deleteEmp(int id) {
		return this.eb.deleteEmp(id);
	}

	public employ findEmpById(int id) {
		return this.eb.findById(id);
	}

	public card fingCardById(int id) {
		return this.cb.findCardById(id);
	}

	public String newCad(card c) {
		return this.cb.insertCard(c);
	}

	public String coldCard(int carid, String status) {
		return this.cb.updateStatus(carid, status)?"用户被成功冻结":"冻结该用户失败";
	}

	public String addMenu(menu m) {
		return this.mb.addFood(m);
	}

	public String deleteMenu(int id) {
		return this.mb.removeFood(id);
	}

	public String updeteMenu(int id,String name,double pri,int tyid,String spec) {
		return this.mb.changeFood(id, name, pri, tyid, spec);
	}

	public List<menu> showAllMenu() {
		return this.mb.selectAllFood();
	}

	public String selectSpecial(int id) {
		return this.mb.selectSpecial(id);
	}

	public List<menu> selectFood(int typeid) {
		return this.mb.selectFood(typeid);
	}

	public List<employ> findAllEmp() {
		return this.eb.showAllEmp();
	}

	public String setSpecialMenu(int id) {
		return this.mb.setSpecial(id);
	}


}
