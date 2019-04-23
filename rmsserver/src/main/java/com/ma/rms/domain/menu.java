package com.ma.rms.domain;

import java.io.Serializable;

public class menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8787;
	private int meid; //菜单编号
	private String mename; //菜名
	private double meprice; //价格
	private String ifspecials; //是否是特价菜
	private int typeid;  //菜品类型号
	private String typename; 
	private double num;
	public menu(int meid, String mename, double meprice, int typeid ,String ifspecials, double num) {
	super();
	this.meid = meid;
	this.mename = mename;
	this.meprice = meprice;
	this.ifspecials = ifspecials;
	this.typeid = typeid;
	this.num = num;
}
	public menu(int meid, String mename, double meprice, int typeid, String ifspecials) {
		super();
		this.meid = meid;
		this.mename = mename;
		this.meprice = meprice;
		this.ifspecials = ifspecials;
		this.typeid = typeid;
	}
	public menu() {
		super();
	}
	public int getMeid() {
		return meid;
	}
	public void setMeid(int meid) {
		this.meid = meid;
	}
	public String getMename() {
		return mename;
	}
	public void setMename(String mename) {
		this.mename = mename;
	}
	public double getMeprice() {
		return meprice;
	}
	public void setMeprice(double meprice) {
		this.meprice = meprice;
	}
	public String getIfspecials() {
		return ifspecials;
	}
	public void setIfspecials(String ifspecials) {
		this.ifspecials = ifspecials;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
}
