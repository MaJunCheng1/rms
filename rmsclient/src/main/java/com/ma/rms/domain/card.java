package com.ma.rms.domain;

public class card {
	private int carid;
	private int eid;
	private int payid;
	private String status;
	private double balance;
	public card(int carid, int eid, int payid, double balance, String status) {
		super();
		this.carid = carid;
		this.eid = eid;
		this.payid = payid;
		this.status = status;
		this.balance = balance;
	}
	public card() {
		super();
	}
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getPayid() {
		return payid;
	}
	public void setPayid(int payid) {
		this.payid = payid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
