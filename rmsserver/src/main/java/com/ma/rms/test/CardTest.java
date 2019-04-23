package com.ma.rms.test;

import org.junit.Test;

import com.ma.rms.dao.cardDao;
import com.ma.rms.dao.impl.cardDaoImpl;
import com.ma.rms.domain.card;

public class CardTest {
//	@Test
	public void insertCard() {
		cardDao cd=new cardDaoImpl();
		boolean b = cd.insertCard(new card(1, 1, 1, 32, "活跃"));
		System.out.println(b);
	}
//	@Test
	public void updateBalance() {
		cardDao cd=new cardDaoImpl();
		boolean b = cd.updateBalance(1, 20);
		System.out.println(b);
	}
//	@Test
	public void  updateStatus() {
		cardDao cd=new cardDaoImpl();
		boolean b = cd. updateStatus(1,"冻结");
		System.out.println(b);
	}
//	@Test
	public void  selectCardById() {
		cardDao cd=new cardDaoImpl();
		card c = cd.selectCardById(1);
		System.out.println(c.getStatus());
	}
}
