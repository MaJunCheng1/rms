package com.ma.rms.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.ma.rms.domain.vegetType;
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
	//代理接口
	private ReceptionService rs;
	
	//用于登录时获取员工信息
	private employ emp;
	//用于获取生成订单号
	private String orid;
	//用于获取菜单编号
	private int carid;

	// 构造方法
	public Control() {
		this.ui = new UserInput();
		this.v = new View();
		// 创建代理对象
		rs = ProxyClient.getClient(ReceptionService.class, IP, PORT);
	}

	public void start() {
		while (true) {
			v.welcom();
			employ em = rs.entry(ui.getString("请输入账号："), ui.getString("请输入密码："));
			v.println("");
			//账号密码输入错误时再次输入
			if (em == null) {
				System.out.println("账号密码错误");
			} else if ("员工".equals(em.getJobtype())) {
				emp = em;
				v.println("欢迎员工" + em.getEname());
				v.println("");
				this.seenotice();
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
					}else if(select==6) {
						changePass();
						v.println("");
					}else if(select == 0) {
						this.backview();
						v.println("欢迎本次使用");
						System.exit(0);
					}else {
						System.out.println("输入有误!!!!");
						v.println("");
					}
				}
			} else if ("经理".equals(em.getJobtype())) {
				emp = em;
				v.println("欢迎" + em.getEname()+"经理");
				while(true){
					v.manager();
					int select=ui.getInt("请选择：");
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
					}else if(select==6) {
						showMSalesVolume();
					}else if(select==7) {
						mostFood();
					}else if(select==8) {
						this.acceptView();
					}else if(select==9) {
						this.changePass();
					}else if(select==10) {
						this.addnotice();
					}else if(select==0) {
						v.println("欢迎本次使用");
						System.exit(0);
					}else{
						System.err.println("请输入有效的选项！！！");
					}
				}
			}
		}
	}

	// 点菜的功能
	public void ordermenu() {
		//生成订单号
		String uid = UUID.randomUUID().toString().replace("-", "");
		orid = uid;
		Date d = new Date();
		carid = ui.getInt("请输入卡号：");
		//添加订单
		rs.addOrders(new orders(uid, d, emp.getEid(), carid, ui.getInt("请输入地址编号：")));
		//获取菜单表
		List<menu> list = rs.selectAllFood();
		v.println(" ");
		v.println("所有菜品如下：");
		v.println("编号\t名称\t价格\t类型");
		//循环遍历
		for (menu m : list) {
			String s = rs.findtypename(m.getTypeid());
			System.out.println(m.getMeid() + "\t" + m.getMename() + "\t" + m.getMeprice() + "\t" + s);
		}
		v.println("");
		one: while (true) {
			v.emone();
			while(true) {
				int select2 = ui.getInt("请选择：");
				//添加菜
				if (select2 == 1) {
					int meid = ui.getInt("请选择菜品编号：");
					menu me = rs.selectFoodById(meid);
					if (me == null) {
						System.out.println("没有该编号");
						break;
					}
					double num = ui.getDouble("请输入你要的份数：");
					rs.addOrderitem(new orderitem(uid, meid, num));
					break;
					//删除菜
				} else if (select2 == 2) {
					int id = ui.getInt("请输入你要删除的菜品编号：");
					double num2 = ui.getDouble("请输入份数：");
					orderitem oi = rs.selectOrderByoridandmeid(uid, id);
					if(oi==null) {
						v.println("购物车里没有该菜品!");
					}else {
						if ((oi.getNum() - num2) > 0) {
							rs.updateOrderitem(uid, num2);
						} else {
							rs.deleteOneOrderitem(uid, id);
						}
						v.println("删除成功");
					}
					break;
					//显示点的所有菜
				} else if (select2 == 3) {
					List<orderitem> list2 = rs.selectitem(uid);
					System.out.println("编号\t名称\t价格\t数量");
					for (orderitem oi : list2) {
						menu me = rs.selectFoodById(oi.getMeid());
						System.out.println(oi.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice()+"\t"+oi.getNum());
					}
					break;
				}else {
					v.println("输入有误!");
				}
			}
			v.println("1.继续点菜");
			v.println("2.提交订单");
			v.println("3.取消订单");
			while(true) {
				int select3 = ui.getInt("请选择：");
				//点菜时继续点菜时跳出这层循环,继续点菜
				if (select3 == 1) {
					break;
				//提交订单,去结账并跳出标记为one的循环
				} else if (select3 == 2) {
					checkout();
					break one;
				//取消订单,删除该订单和订单项
				} else if (select3 == 3) {
					System.out.println("已取消订单");
					rs.deleteOrders(uid);
					return;
				} else {
					System.out.println("输入有误");
					break;
				}
			}

		}

	}
	// 结账的功能
	public void checkout() {
		@SuppressWarnings("unused")
		String payname;
		//获取所有订单号相同的订单项
		List<orderitem> list = rs.selectitem(orid);
		//找到你的所在地
		orders or = rs.selectOrdersById(orid);
		locate loc = rs.selectLocateById(or.getLocid());
		
		//没打折的钱
		double sum = 0;
		//打完折之后的钱
		double actual;
		while (true) {
			sum = 0;
			actual = 0;
			//获取总消费金额
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
				//卡冻结的话交易结束
				if("冻结".equals(c.getStatus())) {
					v.println("你的卡已被冻结,交易结束!");
					rs.deleteOrders(orid);
					return;
				}else {
					if (c.getPayid() == 1) {
						payname = "超级会员";
						actual = sum * 0.8;
					} else {
						payname = "普通会员";
						actual = sum * 0.9;
					}
					//余额不足的话用现金支付
					if((c.getBalance()-actual)<0) {
						v.println("您的余额不足,请用现金支付!");
					}else {
						// 修改余额
						rs.updateCard(carid, actual);
					}
					break;
				}
			} else {
				System.out.println("输入有误");
			}
		}
		//删除订单项(num为0)
		rs.deleteOrderitem();
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
		v.println("----------------------------------------");
		v.println("(***打印副本***)");
		v.println("----------------------------------------");
		v.println("消费金额：" + sum);
		v.println("折扣金额：" + (sum - actual));
		v.println("应收金额：" + actual);
		v.println("");
		card ca= rs.selectCardById(or.getCarid());
		if(ca.getBalance()<50) {
			v.println("！！！你的卡内余额不足50，请尽快充值！！！");
		}
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
	}
	// 充值的功能
	public void deposit() {
		int cid = ui.getInt("请输入卡号：");
		//获取当前日期
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.DATE);
		double balance = ui.getDouble("请输入你要充值的金额(28号冲200及以上赠50哦)：");
		if (balance >= 200 && d == 28) {
			v.println(rs.deposit(cid, (balance + 50)));
		} else {
			v.println(rs.deposit(cid, balance));
		}
		card c = rs.selectCardById(cid);
		v.println("卡内余额："+c.getBalance());
	}
	// 修改密码
	public void changePass() {
		while(true) {
			String pass1 = ui.getString("请输入新密码：");
			String pass2 = ui.getString("请重新输入密码：");
			if(pass1.equals(pass2)) {
				emp.setPassword(pass1);
				v.println(rs.updatePass(emp.getEid(),pass1));
				break;
			}else {
				v.println("你两次输入的密码不一致");
			}	
		}
	}



	//1.添加员工
	public void addEmp(){
		ij:	while(true){
			int i=ui.getInt("请输入员工编号：");
			employ e = rs.findEmpById(i);
			if(e!=null){
				System.err.println("该用户编号已存在！");
				break;
			}
			String name = ui.getString("请输入员工姓名：");
			String accout= ui.getString("请输入用户名：");
			List<employ> list = rs.findAllEmp();
			for (employ em : list) {
				if(em.getAccout().equals(accout)){
					System.err.println("该用户名已存在！");
					break ij;
				}
			}
			String pass= ui.getString("请输入密码：");
			String jobType=ui.getString("请输入工作类型(员工或经理)：");
			if(!jobType.equals("经理")&&!jobType.equals("员工")){
				System.err.println("请输入员工或经理！");
				break;
			}
			String sex= ui.getString("请输入性别(男或女)：");
			if(!sex.equals("男")&&!sex.equals("女")){
				System.err.println("请输入男或女！");
				break;
			}
			String s = rs.addEmp(new employ(i,name ,accout,pass, jobType,sex));	
			System.out.println(s);
			break;
		}
	}
	//2.删除员工
	public void deleteEmp(){
		List<employ> list = rs.findAllEmp();
		System.out.println("当前所有职员");
		System.out.println("员工编号/t员工姓名/t职位/t性别");
		for (employ emp : list) {
			System.out.println(emp.getEid()+"\t"+emp.getEname()+"\t"+emp.getJobtype()+"\t"+emp.getSex());
		}
		v.println(rs.deleteEmp(ui.getInt("请选择要删除的员工编号：")));
	}
	//3.补卡
	public void newCard(){
		//先查看原来卡的内容
		while(true){
			card c = rs.fingCardById(ui.getInt("请输入原有卡号："));
			if(c==null){
				System.err.println("卡号不存在");
				System.out.println();
				break;
			}else{
				//将原来卡的内容复制到新卡
				int i = ui.getInt("请输入要补办的卡号：");
				String s = rs.newCad(new card(i, c.getEid(), c.getPayid(), c.getBalance(), "活跃"));
				//将原来卡冻结
				rs.coldCard(c.getCarid(), "冻结");
				if("开卡成功".equals(s)) {
					v.println("补卡成功");
					v.println("卡号\t余额");
					v.println(i+"\t"+c.getBalance());
				}
				break;
			}				
		}	
	}
	//4.冻结客户
	public void coldCard(){
		while(true){
			int i=ui.getInt("请输入要冻结的卡号：");
			employ em = rs.findEmpById(i);
			if(em==null){
				System.err.println("卡号不存在,请重新输入");
				System.out.println("-----------------");
			}else{
				String s = rs.coldCard(i,"冻结");
				System.out.println(s);
				break;
			}			
		}		
	}
	//5.菜品管理
	public void vegeManage(){
		while(true){
			v.vegemana();
			int i = ui.getInt("请选择：");
			if(i==1){
				this.addmenu();
			}else if(i==2){
				this.deletemenu();
			}else if(i==3){
				this.setSpexialMenu();
			}else if(i==4) {
				this.cancelSpecial();
			}else if(i==5){
				this.showMenu();
			}else if(i==0) {
				break;
			}else {
				System.out.println("输入有误!!!!");
			}
		}
	}
	//添加菜品
	public void addmenu(){
		p:	while(true){
			int i=ui.getInt("请输入菜的编号：");
			menu m = rs.selectFoodById(i);
			if(m!=null){
				System.err.println("菜品编号已存在");
				break;
			}
			String name= ui.getString("请输入菜的名称：");
			List<menu> list = rs.selectAllFood();
			for (menu me : list) {
				if(me.getMename().equals(name)){
					System.err.println("已存在");
					break p;
				}			
			}
			double price= ui.getDouble("请输入菜的单价：");
			List<vegetType> li = rs.showAllType();
			System.out.println("类型编号\t类型名称");
			for (vegetType ve : li) {
				System.out.println(ve.getId()+"\t"+ve.getTypename());
			}
			int typeid= ui.getInt("请输入菜所属的类型编号：");
			String is= ui.getString("请输入是否是特价菜：");
			if(!is.equals("男")&&!is.equals("女")){
				System.err.println("请输入是或否！");
				break;
			}
			String s = this.rs.addMenu(new menu(i,name,price,typeid,is));
			System.out.println(s);
		}		
	}
	//删除菜品
	public void deletemenu(){
		List<vegetType> li = rs.showAllType();
		System.out.println("类型编号\t类型名称");
		for (vegetType ve : li) {
			System.out.println(ve.getId()+"\t"+ve.getTypename());
		}
		List<menu> list = rs.selectFood(ui.getInt("请选择菜品类型编号："));
		System.out.println("菜品ID\t菜品名称\t菜品单价");
		for (menu me : list) {
			System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice());
		}
		String s = rs.deleteMenu(ui.getInt("请选择要删除的菜的编号："));
		System.out.println(s);
	}
	//设置特价菜
	public void setSpexialMenu(){
		v.println("");
		List<vegetType> li = rs.showAllType();
		System.out.println("类型编号\t类型名称");
		//遍历输出菜品类型的编号和名称
		for (vegetType ve : li) {
			System.out.println(ve.getId()+"\t"+ve.getTypename());
		}
		List<menu> list = rs.selectFood(ui.getInt("请选择菜品类型编号："));
		System.out.println("菜品ID\t菜品名称\t菜品单价");
		for (menu me : list) {
			System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice());
		}
		int a=ui.getInt("请输入要设置的特价菜编号：");
		menu me = rs.selectFoodById(a);
		if("是".equals(me.getIfspecials())) {
			v.println("该菜本来就是特价菜！");
		}else {
			String s = rs.selectSpecial("是",a);
			String s1 = rs.setSpecialMenu(rs.selectFoodById(a).getMeprice()*0.3,a);
			System.out.println(s+s1);
		}
	}
	//取消特价菜
	public void cancelSpecial() {
		v.println("");
		List<vegetType> li = rs.showAllType();
		System.out.println("类型编号\t类型名称");
		//遍历输出菜品类型的编号和名称
		for (vegetType ve : li) {
			System.out.println(ve.getId()+"\t"+ve.getTypename());
		}
		List<menu> list = rs.selectFood(ui.getInt("请选择菜品类型编号："));
		System.out.println("菜品ID\t菜品名称\t菜品单价");
		for (menu me : list) {
			System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice());
		}
		int a=ui.getInt("请输入要取消的特价菜编号：");
		menu me = rs.selectFoodById(a);
		if("是".equals(me.getIfspecials())) {
			String s = rs.selectSpecial("否",a);
			String s1 = rs.setSpecialMenu(rs.selectFoodById(a).getMeprice()/0.3,a);
			v.println("该菜已不是特价菜");
		}else {
			v.println("该菜本来就不是特价菜！");
		}
		
	}
	//查看菜单
	public void showMenu(){
		List<menu> list = rs.showAllMenu();
		System.out.println("所有菜单如下");
		v.println("编号\t名称\t价格\t类型\t是否是特价菜");
		for (menu me : list) {
			String s = rs.findtypename(me.getTypeid());
			System.out.println(me.getMeid()+"\t"+me.getMename()+"\t"+me.getMeprice()+"\t"+s+"\t"+me.getIfspecials());
		}
	}
	//查看本月销售量
	@SuppressWarnings("deprecation")
	public void showMSalesVolume() {
		double sum=0;
		List<orderitem> list = rs.selectAllitem();
		Calendar cal = Calendar.getInstance();
		int d = cal.get(Calendar.MONTH);
		for (orderitem oi : list) {
			orders orders = rs.selectOrdersById(oi.getOrid());
			menu menu = rs.selectFoodById(oi.getMeid());
			if(orders.getOrtime().getMonth()==d) {
				sum+=oi.getNum()*menu.getMeprice();
			}
		}
		v.println((d+1)+"月总销售额为："+sum);
		v.println("");
	}
	//查看最受欢迎的菜
	public void mostFood() {
		List<orderitem> list = rs.selectSumNum();
		int i = list.get(0).getMeid();
		menu me = rs.selectFoodById(i);
		double num=list.get(0).getNum();
		v.println("最受欢迎的菜是："+me.getMename());
		v.println("销量是："+num);
	}

	//意见反馈
	public void backview(){
		//普通员工退出前输入客户提出的意见反馈给经理
		String sview = ui.getString("请输入客户意见：");
		PrintWriter pw=null;
		try {
			//写出到意见文本
			pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("view.txt",true)));
			pw.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+":"+sview);
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null)pw.close();
		}
	}
	//经理接收意见
	public void acceptView(){
		//创建输入流
		BufferedReader br=null;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream("view.txt"),"UTF-8"));
			String s="";
			String date = ui.getString("请输入日期：");
			while((s=br.readLine())!=null){
				String[] ss = s.split(":");
				if(date.equals(ss[0])){
					System.out.println(ss[1]);
				}				
			}
			v.println("");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//添加公告
		public void addnotice(){
			//普通员工退出前输入客户提出的意见反馈给经理
			String snotice = ui.getString("请输入公告内容：");
			PrintWriter pw=null;
			try {
				//写出到意见文本
				pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("notice.txt",true)));
				pw.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+":"+snotice);
				pw.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally{
				if(pw!=null)pw.close();
			}
		}
		//员工查看公告
		public void seenotice(){
			//创建输入流
			BufferedReader br=null;
			try {
				br=new BufferedReader(new InputStreamReader(new FileInputStream("notice.txt"),"UTF-8"));
				v.println("公告内容为：");
				String s="";
				while((s=br.readLine())!=null){
					v.println(s);				
				}
				v.println("");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(br!=null)br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		//生成Excel表
		public void generateExcel() {
			rs.generateMenuExcel();
			rs.generateOrderExcel();
			rs.generateTypeExcel();
			v.println("Excel表已在E盘根目录下生成");
		}
}
