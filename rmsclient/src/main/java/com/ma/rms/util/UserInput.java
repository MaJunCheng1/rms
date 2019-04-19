package com.ma.rms.util;

import java.util.Scanner;

//工具类   用于处理用户输入信息的验证
public class UserInput {
	
	//用于接收String
	public String getString(String msg) {
		System.out.print(msg);
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	
	//接收整数
	public int getInt(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				Scanner sc = new Scanner(System.in);
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确,请输入整数类型!");
			}
		}
	}
	
	//接收浮点数
	public double getDouble(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				Scanner sc = new Scanner(System.in);
				return sc.nextDouble();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确,请输入小数类型!");
			}
		}
	}
}
