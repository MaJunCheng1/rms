package com.ma.rms.domain;

import java.io.Serializable;

public class vegetType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String typename;
	public vegetType() {
		super();
	}
	public vegetType(int id, String typename) {
		super();
		this.id = id;
		this.typename = typename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
}
