package com.ma.rms.test;

import java.util.List;

import org.junit.Test;

import com.ma.rms.dao.empDao;
import com.ma.rms.dao.impl.empDaoImpl;
import com.ma.rms.domain.employ;

public class EmpTest {
//	@Test
	public void insertEmp() {
		empDao ed=new empDaoImpl();
		boolean b = ed.insertEmp(new employ(2, "李四", "111", "111", "员工", "男"));
		System.out.println(b);
	}
	
//	@Test
	public void deleteEmp() {
		empDao ed=new empDaoImpl();
		boolean b = ed.deleteEmp(2);
		System.out.println(b);
	}
//	@Test
	public void findAllEmp() {
		empDao ed=new empDaoImpl();
		 List<employ> list = ed.findAllEmp();
		for (employ em : list) {
			System.out.println(em.getEname());
		}
	}
	@Test
	public void findOneEmp() {
		empDao ed=new empDaoImpl();
		employ em = ed.findOneEmp("111","111");
		System.out.println(em.getEname());
	}
	
//	@Test
	public void findById(){
		empDao ed=new empDaoImpl();
		employ em = ed.findById(1);
		System.out.println(em.getEname());
	}
}
