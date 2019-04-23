package com.ma.rms.domain;

import java.io.Serializable;

public class locate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7676;
	private int locid; //所在地编号
	private String locname; //所在地名称
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
