package com.ma.rms.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ma.rms.domain.card;
import com.ma.rms.domain.employ;
import com.ma.rms.domain.locate;
import com.ma.rms.domain.menu;
import com.ma.rms.domain.orderitem;
import com.ma.rms.domain.orders;
import com.ma.rms.domain.shopcar;
import com.ma.rms.service.BackstageService;
import com.ma.rms.service.ReceptionService;
import com.ma.rms.util.UserInput;
import com.ma.rms.view.View;

public class Control {
	// 属性
	private UserInput ui;
	private View v;
	private static final String IP = "10.10.49.167";
//	private static final String IP = "localhost";
	private static final int PORT = 5555;
	private ReceptionService rs;
	private BackstageService bs;

	private employ emp;
	private String orid;
	private int carid;

	// 构造方法
	public Control() {
		this.ui = new UserInput();
		this.v = new View();
		// 创建代理对象
		rs = ProxyClient.getClient(ReceptionService.class, IP, PORT);
		bs = ProxyClient.getClient(BackstageService.class, IP, PORT);
	}

	public void start() {
		while (true) {
			v.welcom();
			employ em = rs.entry(ui.getString("请输入账号："), ui.getString("请输入密码："));
			v.println("");
			if (em == null) {
				System.out.println("账号密码错误");
			} else if ("员工".equals(em.getJobtype())) {
				emp = em;
				v.println("欢迎" + em.getEname());
				while (true) {
					v.employ();
					int select = ui.getInt("请选择：");
					if (select == 1) {
						this.ordermenu();
						v.println("");
					} else if (select == 2) {
						opencard();
						v.println("");
					} else if (select == 3) {
						loss();
						v.println("");
					} else if (select == 4) {
						jieGua();
						v.println("");
					} else if (select == 5) {
						deposit();
						v.println("");
					} else {
						System.out.println("输入有误!!!!");
						v.println("");
					}
				}
			} else if ("经理".equals(em.getJobtype())) {
				while(true){
					v.manager();
					int select=ui.getInt("请选择:");
					if(select==1){
						this.addEmp();
					}else if(select==2){
						this.deleteEmp();
					}else if(select==3){
						this.newCard();
					}else if(select==4){
						this.coldCard();
					}else if(select==5){
						this.vegeManage();
					}else{
						System.err.println("请输入有效的选项！！！");
					}
				}
			}
		}
	}

	// 点菜的功能
	public void ordermenu() {
		String uid = UUID.randomUUID().toString().replace("-", "");
		orid = uid;
		Date d = new Date();
		carid = ui.getInt("请输入卡号：");
		rs.addOrders(new orders(uid, d, emp.getEid(), carid, ui.getInt("请输入地址编号：")));
//		String ortime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<menu> list = rs.selectAllFood();
		v.println(" ");
		v.println("所有菜品如下：");
		v.println("编号\t名称\t价格\t类型");
		for (menu m : list) {
			String s = rs.findtypename(m.getTypeid());
			System.out.println(m.getMeid() + "\t" + m.getMename() + "\t" + m.getMeprice() + "\t" + s);
		}
		v.println("");
		while (true) {
			v.emone();
			int select2 = ui.getInt("请选择：");
			if (select2 == 1) {
				int meid = ui.getInt("请选择菜品编号：");
				menu me = rs.selectFoodById(meid);
				if (me == null) {
					System.out.println("没有该编号");
					break;
				}
				double num = ui.getDouble("请输入你要的份数：");
				rs.addOrderitem(new orderitem(uid, meid, num));
			} else if (select2 == 2) {
				int id = ui.getInt("请输入你要删除的菜品编号：");
				double num2 = ui.getDouble("请输入份数：");
				orderitem oi = rs.selectOrderByoridandmeid(uid, id);
				if ((oi.getNum() - num2) > 0) {
					rs.updateOrderitem(uid, num2);
				} else {
					rs.deleteOneOrderitem(uid, id);
				}
			} else if (select2 == 3) {
				List<orderitem> list2 = rs.selectitem(uid);
				System.out.println("编号\t名称\t价格\t数量");
				for (orderitem oi : list2) {
					menu me = rs.selectFoodById(oi.getMeid());
					System.out.println(oi.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice()+"\t"+oi.getNum());
				}
			}
			v.println("1.继续点菜");
			v.println("2.提交订单");
			v.println("3.取消订单");
			int select3 = ui.getInt("请选择：");
			if (select3 == 1) {
			} else if (select3 == 2) {
				checkout();
				break;
			} else if (select3 == 3) {
				System.out.println("已取消订单");
//				rs.deleteOrderitem(uid);
				rs.deleteOrders(uid);
				return;
			} else {
				System.out.println("输入有误");
			}
		}

	}

	// 结账的功能
	public void checkout() {
		String payname;
		List<orderitem> list = rs.selectitem(orid);
		orders or = rs.selectOrdersById(orid);
		locate loc = rs.selectLocateById(or.getLocid());
		double sum = 0;
		double actual;
		while (true) {
			sum = 0;
			actual = 0;
			for (orderitem oi : list) {
				menu me = rs.selectFoodById(oi.getMeid());
				if ("是".equals(me.getIfspecials())) {
					sum += me.getMeprice() * oi.getNum() * 0.8;
				} else {
					sum += me.getMeprice() * oi.getNum();
				}
			}
			v.println("1.现金");
			v.println("2.会员卡");
			int i = ui.getInt("请选择：");
			if (i == 1) {
				actual = sum;
				break;
			} else if (i == 2) {
				card c = rs.selectCardById(or.getCarid());
				if("冻结".equals(c.getStatus())) {
					v.println("你的卡已被冻结,交易结束!");
					return;
				}else {
					if (c.getPayid() == 1) {
						payname = "超级会员";
						actual = sum * 0.8;
					} else {
						payname = "普通会员";
						actual = sum * 0.9;
					}
					break;
				}
			} else {
				System.out.println("输入有误");
			}
		}

		// 打印小票啦
		v.println("");
		v.println("\t\t亚惠餐厅");
		v.println("");
		v.println("订单号：" + orid);
		v.println("开单人：\t" + emp.getEname());
		v.println("地址：\t" + loc.getLocname());
		v.println("时间：\t" + new SimpleDateFormat("yyyy-MM-dd").format(or.getOrtime()));
		v.println("");
		v.println("----------------------------------------");
		v.println("");
		v.println("菜名 \t 数量 \t 价格 \t 金额");
		for (orderitem oi : list) {
			menu me = rs.selectFoodById(oi.getMeid());
			orderitem order = rs.selectOrderByoridandmeid(orid, me.getMeid());
			if ("是".equals(me.getIfspecials())) {
				v.println(me.getMename() + "(特价菜)\t" + order.getNum() + "\t" + me.getMeprice() + "\t"
						+ me.getMeprice() * oi.getNum() * 0.8);
			} else {
				v.println(me.getMename() + "\t" + order.getNum() + "\t" + me.getMeprice() + "\t"
						+ me.getMeprice() * oi.getNum());
			}
		}
		// 修改余额
		rs.updateCard(carid, actual);
		v.println("----------------------------------------");
		v.println("(***打印副本***)");
		v.println("----------------------------------------");
		v.println("消费金额：" + sum);
		v.println("折扣金额：" + (sum - actual));
		v.println("应收金额：" + actual);
		v.println("");
		v.println("----------------------------------------");
	}

	// 开卡的功能
	public void opencard() {
		int i = ui.getInt("请输入卡号：");
		card c = rs.selectCardById(i);
		if (c != null) {
			System.out.println("已有该卡");
		} else {
			System.out.println( rs.openCard(new card(i, emp.getEid(), ui.getInt("请输入卡的类型(1.超级会员，2普通会员)："), 0, "活跃")));
		}
	}

	// 挂失的功能
	public void loss() {
		int carid = ui.getInt("请输入卡号：");
		card c = rs.selectCardById(carid);
		if("冻结".equals(c.getStatus())) {
			System.out.println("该卡已经是冻结状态!");
		}else {
			if(rs.loss(carid, "冻结")) {
				v.println("挂失成功,已冻结该卡!!");
			}else {
				v.println("挂失失败");
			}
		}
	}

	// 解挂的功能
	public void jieGua() {
		int carid = ui.getInt("请输入卡号：");
		card c = rs.selectCardById(carid);
		if("活跃".equals(c.getStatus())) {
			System.out.println("该卡已经是活跃状态!");
		}else {
			if(rs.loss(carid, "活跃")) {
				v.println("解挂成功,已将该卡解除冻结!!");
			}else {
				v.println("解挂失败");
			}
		}
//		int i = ui.getInt("请输入卡号：");
//		card c= rs.selectCardById(i);
//		rs.loss(i, "冻结");
//		rs.openCard(new card(ui.getInt("请输入新卡号："), c.getEid(), c.getPayid(), c.getBalance(), "活跃"));
	}

	// 充值的功能
	public void deposit() {
		int cid = ui.getInt("请输入卡号：");
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.DATE);
		double balance = ui.getDouble("请输入你要充值的金额(28号冲200及赠50哦)：");
		if (balance >= 200 && d == 28) {
			v.println(rs.deposit(cid, (balance + 50)));
		} else {
			v.println(rs.deposit(cid, balance));
		}
		card c = rs.selectCardById(cid);
		v.println("卡内余额："+c.getBalance());
	}

	//1.添加员工
		public void addEmp(){
			String s = bs.addEmp(new employ(ui.getInt("请输入员工编号"), ui.getString("请输入员工姓名"), ui.getString("请输入用户名"), ui.getString("请输入密码"), ui.getString("请输入工作类型"), ui.getString("请输入性别")));
			System.out.println(s);	
		}
		//2.删除员工
		public void deleteEmp(){
			List<employ> list = bs.findAllEmp();
			System.out.println("当前所有职员");
			System.out.println("员工编号/t员工姓名/t职位/t性别");
			for (employ emp : list) {
				System.out.println(emp.getEid()+"\t"+emp.getEname()+"\t"+emp.getJobtype()+"\t"+emp.getSex());
			}
			bs.deleteEmp(ui.getInt("请选择要删除的员工编号"));
		}
		//3.补卡
		public void newCard(){
			//先查看原来卡的内容
			card c = bs.fingCardById(ui.getInt("请输入要补办的卡号"));
			//将原来卡的内容复制到新卡
			String s = bs.newCad(new card(ui.getInt("请输入卡号"), c.getEid(), c.getPayid(), c.getBalance(), c.getStatus()));
			//将原来卡冻结
			bs.coldCard(c.getCarid(), "冻结");
			System.out.println(s);
		}
		//4.冻结客户
		public void coldCard(){
			String s = bs.coldCard(ui.getInt("请输入要冻结的卡号"),"冻结");
			System.out.println(s);
		}
		//5.菜品管理
		public void vegeManage(){
			while(true){
				v.vegemana();
				int i = ui.getInt("请选择:");
				if(i==1){
					//1.添加菜品
					String s = this.bs.addMenu(new menu(ui.getInt("请输入菜的编号"), ui.getString("请输入菜的名称"), ui.getDouble("请输入菜的单价"), ui.getInt("请输入菜所属的类型编号"), ui.getString("请输入是否是特价菜")));
					System.out.println(s);
				}else if(i==2){
					//删除菜品
					List<menu> list = bs.selectFood(ui.getInt("请选择菜品类型"));
					System.out.println("菜品ID\t菜品名称\t菜品单价");
					for (menu me : list) {
						System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice());
					}
					String s = bs.deleteMenu(ui.getInt("请选择要删除的菜的编号"));
					System.out.println(s);
				}else if(i==3){
					//设置特价菜
					List<menu> list = bs.selectFood(ui.getInt("请选择菜品类型"));
					System.out.println("菜品ID/t菜品名称/t菜品单价");
					for (menu me : list) {
						System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice());
					}
					int a=ui.getInt("请输入要设置的特价菜编号");
					String s = bs.selectSpecial(a);
					String s1 = bs.setSpecialMenu(a);
					System.out.println(s+s1);
				}else if(i==4){
					//查看菜单
					List<menu> list = bs.showAllMenu();
					System.out.println("所有菜单如下");
					for (menu me : list) {
						System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice()+"\t"+me.getTypename()+"\t"+me.getIfspecials());
					}
				}
			}
		}
}
