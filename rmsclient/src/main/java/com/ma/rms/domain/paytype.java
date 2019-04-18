package com.ma.rms.domain;

public class paytype {
	private int payid;
	private String payname;
	private double discount;
	public paytype() {
		super();
	}
	public paytype(int payid, String payname, double discount) {
		super();
		this.payid = payid;
		this.payname = payname;
		this.discount = discount;
	}
	public int getPayid() {
		return payid;
	}
	public void setPayid(int payid) {
		this.payid = payid;
	}
	public String getPayname() {
		return payname;
	}
	public void setPayname(String payname) {
		this.payname = payname;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
