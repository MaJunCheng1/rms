package com.ma.rms.test;

import com.ma.rms.control.ProxyClient;
import com.ma.rms.domain.menu;
import com.ma.rms.service.ReceptionService;
import com.ma.rms.util.UserInput;
import com.ma.rms.view.View;

public class MyTest {
	private static View v;
	private static ReceptionService rs;
	private static UserInput ui;
	private static final String IP = "localhost";
	private static final int PORT = 5555;

	public MyTest() {
		rs= ProxyClient.getClient(ReceptionService.class, IP, PORT);
		this.ui = new UserInput();
		this.v=new View();
	}
	public static void main(String[] args) {
		MyTest m=new MyTest();
		while(true) {
			int a=ui.getInt("请输入要设置的特价菜编号：");
			menu me = rs.selectFoodById(a);
			System.out.println(me.getIfspecials().toString());
			System.out.println("否".equals(me.getIfspecials()));
//			if("是".equals(me.getIfspecials())) {
//				v.println("该菜本来就是特价菜！");
//			}else {
//				String s = rs.selectSpecial("是",a);
//				String s1 = rs.setSpecialMenu(rs.selectFoodById(a).getMeprice()*0.3,a);
//				System.out.println(s+s1);
//			}
		}
	}
}


