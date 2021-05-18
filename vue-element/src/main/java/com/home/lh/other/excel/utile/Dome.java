package com.home.lh.other.excel.utile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.github.andyczy.java.excel.ExcelUtils;
import com.home.lh.system.entity.SysUser;

public class Dome {
	
	
	public void daoRu(HttpServletResponse response,String[] sheetNameList,String excelName,List<SysUser> list) {
		//【推荐使用该方式】【建议大数据量下不要过多设置样式】
        List<List<String[]>> dataLists = exportData(list);
	    ExcelUtils excelUtils = ExcelUtils.initialization();
	    // 必填项--导出数据（参数请看下面的格式）
	    excelUtils.setDataLists(dataLists);   
	    // 必填项--sheet名称（如果是多表格导出、sheetName也要是多个值！）
	    excelUtils.setSheetName(sheetNameList);
	    // 文件名称(可为空，默认是：sheet 第一个名称)
	    excelUtils.setFileName(excelName);
	    
	    // web项目response响应输出流：必须填 【ExcelUtils 对象】
	    excelUtils.setResponse(response);
	    
	    // 输出本地【LocalExcelUtils 对象】
	    // excelUtils.setFilePath("F://test.xlsx");

	            
	    // 自定义：对每个单元格自定义下拉列表（可为空）
	    HashMap dropDownMap = new HashMap();
	    List<String[]> dropList = new ArrayList<>();
	    //必须放第一：设置下拉列表的列（excel从零行开始数）
	    String[] sheetDropData = new String[]{"0", "1" };
	    
	    //下拉的值放在 sheetDropData 后面。
	    String[] sex = {"男,女"};
	    // 第一列显示的值
	    String[] city = {"北京","山东","海南","湖南"};  // 第二列显示的值
	    
	    
	    dropList.add(sheetDropData);
	    dropList.add(sex);
	    dropList.add(city);
	    //第一个表格设置。
	    dropDownMap.put(1, dropList);
	    
	    excelUtils.setDropDownMap(dropDownMap);
	   
	    // 执行导出
	    excelUtils.exportForExcelsOptimize();
		
		
	}
	
	public List<List<String[]>> exportData(List<SysUser> list){
	       List<List<String[]>> dataLists = new ArrayList<>();
	       List<String[]> oneList = new ArrayList<>();  // 表格一数据
	      
	       String[] valueString = null;

	       String[] headers = {"账号","密码",};
	    
	       oneList.add(headers);
	    
	        
	       for (int i = 0; i < list.size(); i++) {
	           valueString = new String[]{(i + 1) + "", list.get(i).getUserName(),list.get(i).getPassWord()};
	           oneList.add(valueString);
	       }
	       
	       dataLists.add(oneList);
	       return dataLists;
	   }   
	

}
