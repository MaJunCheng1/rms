package com.ma.rms.biz.impl;

import com.ma.rms.biz.locateBiz;
import com.ma.rms.dao.locateDao;
import com.ma.rms.dao.impl.locateDaoImpl;
import com.ma.rms.domain.locate;

public class locateBizImpl implements locateBiz {
	private locateDao ld;
	
	public locateBizImpl() {
		super();
		ld=new locateDaoImpl();
	}

	public locate findLocate(int id) {
		return ld.findLocate(id);
	}

}
