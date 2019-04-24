package com.ma.rms.biz.impl;

import java.util.List;

import com.ma.rms.biz.orderitemBiz;
import com.ma.rms.dao.orderitemDao;
import com.ma.rms.dao.impl.orderitemDaoImpl;
import com.ma.rms.domain.orderitem;

public class orderitemBizImpl implements orderitemBiz {
	private orderitemDao od;
	
	public orderitemBizImpl() {
		super();
		od=new orderitemDaoImpl();
	}

	public String insertOrderitem(orderitem oi) {
		return od.addOrderitem(oi)?"添加成功":"添加失败";
	}

	public String changeOrderitem(String orid, double num) {
		return od.updateOrderitem(orid, num)?"已从购物车里删除":"修改失败";
	}

	public String removeOrderitem() {
		return od.deleteOrderitem()?"删除成功":"删除失败";
	}

	public String removeOneOrderitem(String orid, int meid) {
		return od.deleteOneOrderitem(orid, meid)?"已从购物车里删除":"修改失败";
	}

	public List<orderitem> finditem(String orid) {
		return od.selectitem(orid);
	}

	public orderitem findOrderByoridandmeid(String orid, int meid) {
		return od.selectOrderByoridandmeid(orid, meid);
	}
	
	//查询所有订单项
	public List<orderitem> findAllitem(){
		return od.selectAllitem();
	}
	//用meid进行分类查找每个菜的数量
	public List<orderitem> findSumNum(){
		return od.selectSumNum();
	}
}
