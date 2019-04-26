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
import com.ma.rms.excel.MenuExcel;
import com.ma.rms.excel.OrderitemExcel;
import com.ma.rms.excel.VegetypeExcel;
import com.ma.rms.service.ReceptionService;

public class ReceptionServiceImpl implements ReceptionService{
	private empBiz eb;  //员工
	private cardBiz cb;  //卡
	private menuBiz mb;  // 菜单
	private ordersBiz ob; //订单
	private orderitemBiz oib;  //订单项
	private locateBiz lb;  //所在地
	private MenuExcel me;   //菜单表
	private OrderitemExcel oe;  //订单项表
	private VegetypeExcel ve;  //菜品类型表
	//构造方法中实力化
	public ReceptionServiceImpl() {
		this.eb=new empBizImpl();
		this.cb=new cardBizImpl();
		this.mb=new menuBizIpml();
		this.ob=new ordersBizImpl();
		this.oib=new orderitemBizImpl();
		this.lb=new locateBizImpl();
		this.me=new MenuExcel();
		this.oe=new OrderitemExcel();
		this.ve=new VegetypeExcel();
	}
	//登录方法
	public employ entry(String accout, String password) {
		return eb.findOneEmp(accout, password);
	}
	//查询所有菜品
	public List<menu> selectAllFood() {
		return mb.selectAllFood();
	}
	//根据菜品类型ID查找类型名称
	public String findtypename(int typeid) {
		return mb.selecttypename(typeid);
	}
	//根据菜品id查找某个菜
	public menu selectFoodById(int meid) {
		return mb.selectFoodById(meid);
	}
	//添加订单的方法
	public String addOrders(orders o) {
		return ob.insertOrders(o);
	}
	//根据订单号查找订单信息
	public orders selectOrdersById(String orid) {
		return ob.findOrdersById(orid);
	}
	//删除订单功能(根据订单号)
	public String deleteOrders(String orid) {
		return ob.deleteOrders(orid);
	}
	//添加订单项功能
	public String addOrderitem(orderitem oi) {
		return oib.insertOrderitem(oi);
	}
	//根据订单号修改订单信息(菜的数量)
	public String updateOrderitem(String orid,int meid, double num) {
		return oib.changeOrderitem(orid,meid, num);
	}
	//删除订单项的方法
	public String deleteOrderitem() {
		return oib.removeOrderitem();
	}
	//删除某一订单中的某一个菜
	public String deleteOneOrderitem(String orid,int meid) {
		return oib.removeOneOrderitem(orid, meid);
	}
	//查询所有订单项方法
	public List<orderitem> selectitem(String orid) {
		return oib.finditem(orid);
	}
	//查看订单的某一个菜根据订单id和菜品id
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
	//根据地址id查询地址相对名称
	public locate selectLocateById(int locid) {
		return lb.findLocate(locid);
	}
	//修改卡内余额的方法根据卡的id
	public String updateCard(int carid, double money) {
		return cb.updateBalance(carid, money);
	}
	//根据卡号查询卡片信息
	public card selectCardById(int carid) {
		return cb.findCardById(carid);
	}
	//开卡功能
	public String openCard(card ca) {
		return cb.insertCard(ca);
	}
	//挂失功能根据卡号
	public boolean loss(int carid, String status) {
		return cb.updateStatus(carid, status);
	}
	//消费修改余额
	public String deposit(int carid, double balance) {
		return cb.deposit(carid, balance);
	}
	//修改密码
	public	String updatePass(int eid,String newPass) {
		return eb.changePass(eid,newPass);
	}
	//添加员工功能
	public String addEmp(employ e) {
		return this.eb.insertEmp(e);
	}
	//删除员工功能根据员工编号
	public String deleteEmp(int id) {
		return this.eb.deleteEmp(id);
	}
	//根据员工编号查找某个员工信息
	public employ findEmpById(int id) {
		return this.eb.findById(id);
	}
	//根据卡号查找卡片信息
	public card fingCardById(int id) {
		return this.cb.findCardById(id);
	}
	//补卡功能,开一张新卡
	public String newCad(card c) {
		return this.cb.insertCard(c);
	}
	//冻结卡的功能
	public String coldCard(int carid, String status) {
		return this.cb.updateStatus(carid, status)?"用户被成功冻结":"冻结该用户失败";
	}
	//添加菜品功能
	public String addMenu(menu m) {
		return this.mb.addFood(m);
	}
	//删除菜品功能
	public String deleteMenu(int id) {
		return this.mb.removeFood(id);
	}
	//根据菜品编号修改菜品信息
	public String updeteMenu(int id,String name,double pri,int tyid,String spec) {
		return this.mb.changeFood(id, name, pri, tyid, spec);
	}
	//查看所有菜单功能
	public List<menu> showAllMenu() {
		return this.mb.selectAllFood();
	}
	//根据卡号,查找特价菜
	public String selectSpecial(String s,int id) {
		return this.mb.selectSpecial(s,id);
	}
	//根据菜品类型编号返回所有该类型的菜单
	public List<menu> selectFood(int typeid) {
		return this.mb.selectFood(typeid);
	}
	//查看所有员工功能
	public List<employ> findAllEmp() {
		return this.eb.showAllEmp();
	}
	//根据菜品编号设置特价菜价格
	public String setSpecialMenu(double price,int id) {
		return this.mb.setSpecial(price,id);
	}
	//查询所有菜品类型编号及其对应的名称
	public List<vegetType> showAllType() {
		return this.mb.showAllType();
	}
	//生成菜单表
	public void generateMenuExcel() {
		me.generateMenuExcel();
	}
	//生成订单项表
	public void generateOrderExcel() {
		oe.generateOrderExcel();
	}
	//生成菜品种类表
	public void generateTypeExcel() {
		ve.generateTypeExcel();
	}
	public employ selectEmpByAccount(String account) {
		return eb.findEmpByAccount(account);
	}
	
}
