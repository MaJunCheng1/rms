package com.ma.rms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//购物车
public class shopcar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4343;
	private  Map<String,List<menu>> map;
	private List<menu> list;
	public shopcar() {
		map=new HashMap<String, List<menu>>();
		list=new ArrayList<menu>();
	}
	
	//往购物车加菜的功能
	public void add(menu me,double num) {
		list.add(me);
		me.setNum(num);
	}
	
	//从购物车减菜的功能
	public void delete(int meid,double num) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getMeid()==meid) {
				list.remove(i);
			}
		}
	}
	
	//显示菜单的功能
	public void show() {
		for (menu m : list) {
			System.out.println(m.getMeid()+"\t"+m.getMename()+"\t"+m.getMeprice()+"\t"+m.getNum());
		}
	}

	public Map<String, List<menu>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<menu>> map) {
		this.map = map;
	}

	public List<menu> getList() {
		return list;
	}

	public void setList(List<menu> list) {
		this.list = list;
	}
	
	
	
}
