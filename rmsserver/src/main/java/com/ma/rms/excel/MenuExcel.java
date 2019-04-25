  package com.ma.rms.excel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ma.rms.dao.menuDao;
import com.ma.rms.dao.impl.menuDaoImpl;
import com.ma.rms.domain.menu;

public class MenuExcel {
	public void generateMenuExcel() {
		//实例化对象
		menuDao md=new menuDaoImpl();
		//获取菜单列表
		List<menu> list = md.findAllFood();
		//创建工作簿
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个工作表
		HSSFSheet sheet=wb.createSheet("测试");
//		//创建一行(行的索引从0开始)
//		HSSFRow row =sheet.createRow(1);
//		//创建单元格(列的索引从0开始)
//		HSSFCell cell = row.createCell(0);
//		cell.setCellValue("测试");
//		
		//设置列宽
		sheet.setColumnWidth(1,3000);
		sheet.setColumnWidth(4,4000);
//		//width每个空间的大小为n个字符*256   可以容纳n个字符
		
		//创建一行(行的索引从0开始)
		HSSFRow row =sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("编号");
		cell = row.createCell(1);
		cell.setCellValue("名称");
		cell = row.createCell(2);
		cell.setCellValue("价格");
		cell = row.createCell(3);
		cell.setCellValue("类型编号");
		cell = row.createCell(4);
		cell.setCellValue("是否是特价菜");
		//没执行一行自加一
		int i=1;
		for (menu me : list) {
			row =sheet.createRow(i);
			for(int j=0;j<5;j++) {
				cell = row.createCell(j);
				switch (j){
				case 0:
					cell.setCellValue(me.getMeid());
					break;
				case 1:
					cell.setCellValue(me.getMename());
					break;
				case 2:
					cell.setCellValue(me.getMeprice());
					break;
				case 3:
					cell.setCellValue(me.getTypeid());
					break;
				case 4:
					cell.setCellValue(me.getIfspecials());
					break;
				}
			}
			i++;
		}
		
		
		//新建文件
		File file=new File("e:/菜单.xls");
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
