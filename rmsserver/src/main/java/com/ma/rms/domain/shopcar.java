package com.ma.rms.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//购物车
public class shopcar {
	private  Map<String,List<menu>> map;
	private List<menu> list;
	
	public shopcar() {
		map=new HashMap<String, List<menu>>();
		list=new ArrayList<menu>();
	}
	
	//往购物车加菜的功能
	private void add(menu me) {
		list.add(me);
	}
	
	//从购物车减菜的功能
	private void delete(int meid,double num) {
		
	}
	
	//显示菜单的功能
	
}
