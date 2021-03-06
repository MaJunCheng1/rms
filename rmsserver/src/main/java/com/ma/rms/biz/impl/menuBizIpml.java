package com.ma.rms.biz.impl;

import java.util.List;

import com.ma.rms.biz.menuBiz;
import com.ma.rms.dao.menuDao;
import com.ma.rms.dao.impl.menuDaoImpl;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.vegetType;

public class menuBizIpml implements menuBiz{
	menuDao mb;

	public menuBizIpml() {
		super();
		mb=new menuDaoImpl();
	}

	public menu selectFoodById(int meid) {
		return mb.findFoodById(meid);
	}

	public List<menu> selectAllFood() {
		return mb.findAllFood();
	}

	public String addFood(menu me) {
		return mb.insertFood(me)?"添加成功":"添加失败";
	}

	public String removeFood(int meid) {
		return mb.deleteFood(meid)?"删除成功":"删除失败";
	}

	public String changeFood(int meid, String mename, double meprice, int typeid, String ifspecials) {
		return mb.updateFood(meid, mename, meprice, typeid, ifspecials)?"修改成功":"修改失败";
	}

	public List<menu> selectFood(int typeid) {
		return mb.findFood(typeid);
	}

	public String selecttypename(int typeid) {
		return mb.findtypename(typeid);
	}

	public String selectSpecial(String s,int id) {
		return mb.selectSpecial(s,id)?"已选择,":"未选中,";
	}

	public String setSpecial(double price,int id) {
		return mb.setSpecial(price,id)?"设置价格成功":"价格设置出错";
	}

	public List<vegetType> showAllType() {
		return mb.showVegeType();
	}
}
