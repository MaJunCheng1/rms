package com.ma.rms.service.impl;

import java.util.List;

import com.ma.rms.biz.cardBiz;
import com.ma.rms.biz.empBiz;
import com.ma.rms.biz.locateBiz;
import com.ma.rms.biz.menuBiz;
import com.ma.rms.biz.orderitemBiz;
import com.ma.rms.biz.ordersBiz;
import com.ma.rms.biz.impl.cardBizImpl;
import com.ma.rms.biz.impl.empBizImpl;
import com.ma.rms.biz.impl.locateBizImpl;
import com.ma.rms.biz.impl.menuBizIpml;
import com.ma.rms.biz.impl.orderitemBizImpl;
import com.ma.rms.biz.impl.ordersBizImpl;
import com.ma.rms.domain.card;
import com.ma.rms.domain.employ;
import com.ma.rms.domain.locate;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.orderitem;
import com.ma.rms.domain.orders;
import com.ma.rms.domain.vegetType;
import com.ma.rms.service.ReceptionService;

public class ReceptionServiceImpl implements ReceptionService{
	private empBiz eb;
	private cardBiz cb;
	private menuBiz mb;
	private ordersBiz ob;
	private orderitemBiz oib;
	private locateBiz lb;
	public ReceptionServiceImpl() {
		this.eb=new empBizImpl();
		this.cb=new cardBizImpl();
		this.mb=new menuBizIpml();
		this.ob=new ordersBizImpl();
		this.oib=new orderitemBizImpl();
		this.lb=new locateBizImpl();
	}
	public employ entry(String accout, String password) {
		return eb.findOneEmp(accout, password);
	}

	public List<menu> selectAllFood() {
		return mb.selectAllFood();
	}

	public String findtypename(int typeid) {
		return mb.selecttypename(typeid);
	}

	public menu selectFoodById(int meid) {
		return mb.selectFoodById(meid);
	}

	public String addOrders(orders o) {
		return ob.insertOrders(o);
	}

	public orders selectOrdersById(String orid) {
		return ob.findOrdersById(orid);
	}

	public String deleteOrders(String orid) {
		return ob.deleteOrders(orid);
	}
	
	public String addOrderitem(orderitem oi) {
		return oib.insertOrderitem(oi);
	}

	public String updateOrderitem(String orid, double num) {
		return oib.changeOrderitem(orid, num);
	}

	public String deleteOrderitem() {
		return oib.removeOrderitem();
	}
	
	public String deleteOneOrderitem(String orid,int meid) {
		return oib.removeOneOrderitem(orid, meid);
	}
	
	public List<orderitem> selectitem(String orid) {
		return oib.finditem(orid);
	}

	public orderitem selectOrderByoridandmeid(String orid, int meid) {
		return oib.findOrderByoridandmeid(orid, meid);
	}
	//查询所有订单项
	public List<orderitem> selectAllitem(){
		return oib.findAllitem();
	}
	//用meid进行分类查找每个菜的数量
	public List<orderitem> selectSumNum(){
		return oib.findSumNum();
	}

	public locate selectLocateById(int locid) {
		return lb.findLocate(locid);
	}

	public String updateCard(int carid, double money) {
		return cb.updateBalance(carid, money);
	}
	
	public card selectCardById(int carid) {
		return cb.findCardById(carid);
	}

	public String openCard(card ca) {
		return cb.insertCard(ca);
	}

	public boolean loss(int carid, String status) {
		return cb.updateStatus(carid, status);
	}

	public String deposit(int carid, double balance) {
		return cb.deposit(carid, balance);
	}
	//修改密码
	public	String updatePass(int eid,String newPass) {
		return eb.changePass(eid,newPass);
	}

	public String addEmp(employ e) {
		return this.eb.insertEmp(e);
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
	public List<vegetType> showAllType() {
		return this.mb.showAllType();
	}

}
