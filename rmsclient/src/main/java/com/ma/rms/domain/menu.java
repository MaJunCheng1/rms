package com.ma.rms.domain;

public class menu {
	private int meid;
	private String mename;
	private double meprice;
	private char ifspecials;
	private int typeid;
	private String typename;
	private double num;
	public menu(int meid, String mename, double meprice, int typeid, char ifspecials) {
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
	public char getIfspecials() {
		return ifspecials;
	}
	public void setIfspecials(char ifspecials) {
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
