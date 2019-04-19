package com.ma.rms.control;


import com.ma.rms.service.BackstageService;
import com.ma.rms.service.ReceptionService;
import com.ma.rms.util.UserInput;
import com.ma.rms.view.View;



public class Control {
	// 属性
	private UserInput ui;
	private View v;
	private static final String IP = "10.10.49.167";
	private static final int PORT = 5555;
	private ReceptionService rs;
	private BackstageService bs;

	// 构造方法
	public Control() {
		this.ui = new UserInput();
		this.v = new View();
		// 创建代理对象
		rs = ProxyClient.getClient(ReceptionService.class, IP, PORT);
		bs = ProxyClient.getClient(BackstageService.class, IP, PORT);
	}

}
