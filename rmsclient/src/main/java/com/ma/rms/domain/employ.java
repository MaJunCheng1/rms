package com.ma.rms.domain;

public class employ {
	private int eid; //员工id
	private String ename;	
	private String accout;
	private String password;
	private String jobtype;
	private String sex;
	public employ() {
		super();
	}
	public employ(int eid, String ename, String accout, String password, String jobtype, String sex) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.accout = accout;
		this.password = password;
		this.jobtype = jobtype;
		this.sex = sex;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getAccout() {
		return accout;
	}
	public void setAccout(String accout) {
		this.accout = accout;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
