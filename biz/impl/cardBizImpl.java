package com.ma.rms.biz.impl;

import com.ma.rms.biz.cardBiz; 
import com.ma.rms.dao.cardDao;
import com.ma.rms.dao.impl.cardDaoImpl;
import com.ma.rms.domain.card;

public class cardBizImpl implements cardBiz{
	cardDao cd=new cardDaoImpl();
	public String insertCard(card c) {
		return this.cd.insertCard(c)?"开卡成功":"开卡失败";
	}

	public String updateBalance(int carid, double balance) {
		return this.cd.updateBalance(carid, balance)?"刷卡成功":"刷卡失败";
	}

	public String updateStatus(int carid, String status) {
		return this.cd.updateStatus(carid, status)?"办理成功":"办理失败";
	}

}
