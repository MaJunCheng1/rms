package com.ma.rms.control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.ma.rms.service.BackstageService;
import com.ma.rms.service.ReceptionService;
import com.ma.rms.service.impl.BackstageServiceImpl;
import com.ma.rms.service.impl.ReceptionServiceImpl;

public class Control {
	// 属性
//	List<ControlThread> list;
	private static final int PORT = 5555;
	// 创建套接字对象
	private ServerSocket server;
	//创建别代理的对象
	private  ReceptionService rs;
	private BackstageService bs;
	private ExecutorService es;
	// 构造方法
	public Control() {

		try {
			System.out.println("服务器正在启动中....");
			// 创建服务器
			this.server = new ServerSocket(PORT);
//			list=new ArrayList<ControlThread>();
			Thread.sleep(1000);
			System.out.println("服务器已开启!!!!");
			es=Executors.newCachedThreadPool();
			this.rs=new ReceptionServiceImpl();
			this.bs=new BackstageServiceImpl();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 自定义方法
	public void start() {
		while (true) {
			try {
				Socket client = this.server.accept();
				System.out.println("用户" + client.getInetAddress().getHostAddress() + "已连接");
				// 创建线程对象
				ControlThread ct = new ControlThread(client,rs);
				ControlThread ct2 = new ControlThread(client,bs);
				//将线程对象交给线程池管理
				es.submit(ct);
				es.submit(ct2);
				//返回处于活跃状态的线程数量
				int i = ((ThreadPoolExecutor)es).getActiveCount();
				System.out.println("在线人数是"+i);
//				list.add(ct);
//				Thread t=new Thread(ct);
//				System.out.println("当前在线人数:"+list.size());
				// 启动线程
//				ct.start();
//				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
