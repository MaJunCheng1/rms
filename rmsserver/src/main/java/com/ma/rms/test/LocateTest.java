package com.ma.rms.test;

import org.junit.Test;

import com.ma.rms.dao.locateDao;
import com.ma.rms.dao.impl.locateDaoImpl;
import com.ma.rms.domain.locate;

public class LocateTest {
//	@Test
	public void findLocate() {
		locateDao ld=new locateDaoImpl();
		locate lo = ld.findLocate(1);
		System.out.println(lo.getLocname());
	}
}
