package com.ma.rms.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ma.rms.dao.orderitemDao;
import com.ma.rms.dao.impl.orderitemDaoImpl;
import com.ma.rms.domain.orderitem;
import com.ma.rms.domain.vegetType;

public class OrderitemExcel {
	public void generateOrderExcel(){
		//实例化对象
		orderitemDao od=new orderitemDaoImpl();
		//获取所有订单项
		List<orderitem> list = od.selectAllitem();
		//创建工作簿
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个工作表
		HSSFSheet sheet = wb.createSheet();
		//创建一行(行的索引从0开始)
		HSSFRow row = sheet.createRow(0);
		//列
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("订单号");
		cell = row.createCell(1);
		cell.setCellValue("菜品编号");
		cell = row.createCell(2);
		cell.setCellValue("数量");
		
		//宽度
		sheet.setColumnWidth(0, 8500);
		
		//没执行一行自加一
		int i=1;
		for (orderitem o : list) {
			row =sheet.createRow(i);
			for(int j=0;j<3;j++) {
				cell = row.createCell(j);
				switch (j){
				case 0:
					cell.setCellValue(o.getOrid());
					break;
				case 1:
					cell.setCellValue(o.getMeid());
					break;
				case 2:
					cell.setCellValue(o.getNum());
					break;
				}
			}
			i++;
		}

		//新建文件
		File file=new File("e:/订单项.xls");
		try {
			//保存到文件里
			wb.write(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

