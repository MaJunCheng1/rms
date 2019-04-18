package com.ma.rms.domain;

import java.util.Date;

public class orders {
	private String orid;
	private Date ortime;
	private int eid;
	private int carid;
	private int locid;
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public Date getOrtime() {
		return ortime;
	}
	public void setOrtime(Date ortime) {
		this.ortime = ortime;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public int getLocid() {
		return locid;
	}
	public void setLocid(int locid) {
		this.locid = locid;
	}
	public orders() {
		super();
	}
	public orders(String orid, Date ortime, int eid, int carid, int locid) {
		super();
		this.orid = orid;
		this.ortime = ortime;
		this.eid = eid;
		this.carid = carid;
		this.locid = locid;
	}
}
