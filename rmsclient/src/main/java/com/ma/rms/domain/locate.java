package com.ma.rms.domain;

public class locate {
	private int locid;
	private String locname;
	public locate() {
		super();
	}
	public locate(int locid, String locname) {
		super();
		this.locid = locid;
		this.locname = locname;
	}
	public int getLocid() {
		return locid;
	}
	public void setLocid(int locid) {
		this.locid = locid;
	}
	public String getLocname() {
		return locname;
	}
	public void setLocname(String locname) {
		this.locname = locname;
	}
}
