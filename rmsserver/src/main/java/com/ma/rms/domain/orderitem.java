package com.ma.rms.domain;

import java.io.Serializable;

public class orderitem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9898;
	private String orid; //订单号
	private int meid;   //菜品编号
	private double num; //订单数量
	public orderitem(String orid, int meid, double num) {
		super();
		this.orid = orid;
		this.meid = meid;
		this.num = num;
	}
	public orderitem() {
		super();
	}
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public int getMeid() {
		return meid;
	}
	public void setMeid(int meid) {
		this.meid = meid;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	
}
