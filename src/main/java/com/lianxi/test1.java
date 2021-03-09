package com.lianxi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author: lizi
 * @Date: 2020/12/23 下午6:55
 */
public class test1 {
    public static void main(String[] args) {
        String beanId="916350000010070003";
        //System.out.println(beanId.toUpperCase().hashCode());
        System.out.println( Math.abs(beanId.toUpperCase().hashCode()) % 16);
    }
    private XSSFWorkbook ExcelWBook;//整个excel对象
    private XSSFSheet ExcelWSheet;//sheet工作表对象
    private XSSFRow Row;//行对象
    private XSSFCell Cell;//单元格

    public String getTextData(String sheetname,int row,int col){
        String value = "";
        ExcelWSheet = ExcelWBook.getSheet(sheetname);
        try {
            Cell = ExcelWSheet.getRow(row).getCell(col);
            value = Cell.getStringCellValue();
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
