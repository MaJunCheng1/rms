package com.ma.rms.biz.impl;

import com.ma.rms.biz.cardBiz;
import com.ma.rms.dao.cardDao;
import com.ma.rms.dao.impl.cardDaoImpl;
import com.ma.rms.domain.card;

public class cardBizImpl implements cardBiz {
	cardDao cd;
	
	public cardBizImpl() {
		super();
		cd=new cardDaoImpl();
	}

	public String insertCard(card c) {
		return this.cd.insertCard(c)?"开卡成功":"开卡失败";
	}

	public String updateBalance(int carid, double balance) {
		return this.cd.updateBalance(carid, balance)?"刷卡成功":"刷卡失败";
	}

	public boolean updateStatus(int carid, String status) {
		return this.cd.updateStatus(carid, status);
	}

	public card findCardById(int carid) {
		return cd.selectCardById(carid);
	}

	public String deposit(int carid, double balance) {
		return cd.updateBalance(carid, balance)?"充值成功":"充值失败";
	}
}
