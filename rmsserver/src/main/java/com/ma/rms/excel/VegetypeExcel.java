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
import com.ma.rms.domain.vegetType;

public class VegetypeExcel {
	public void generateTypeExcel() {
		//实例化对象
		menuDao md=new menuDaoImpl();
		//获取菜单类型列表
		List<vegetType> list = md.showVegeType();
		//创建工作簿
		HSSFWorkbook wb=new HSSFWorkbook();
		//创建一个工作表
		HSSFSheet sheet=wb.createSheet("测试");
		//创建一行(行的索引从0开始)
		HSSFRow row =sheet.createRow(0);
		
		//设置宽度
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("菜品类型号");
		cell = row.createCell(1);
		cell.setCellValue("菜品类型名");

		//没执行一行自加一
		int i=1;
		for (vegetType v : list) {
			row =sheet.createRow(i);
			for(int j=0;j<2;j++) {
				cell = row.createCell(j);
				switch (j){
				case 0:
					cell.setCellValue(v.getId());
					break;
				case 1:
					cell.setCellValue(v.getTypename());
					break;
				}
			}
			i++;
		}

		//新建文件
		File file=new File("e:/菜单类型.xls");
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
