package org.fos.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

    // For reading excel file need to follow below steps :
    // Read/Access Excel File --> Access WorkBook --> Access Sheet Name --> Access Row & col --> Then Cell data
    public static FileInputStream fileInputStream;
    public static XSSFWorkbook workBook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    // Here first find the value of cell & then the row count & col count of our sheet

    public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo){
        //create the object of fileInputStream
        try {
            fileInputStream = new FileInputStream(fileName);
        //create the object of workbook
            workBook = new XSSFWorkbook(fileInputStream);
        //creating the object of workSheet
        excelSheet = workBook.getSheet(sheetName);
        cell = workBook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);

        workBook.close();
        return cell.getStringCellValue();}
        catch (Exception e){
            return "";
        }
    }

    public static int getRowCount(String fileName, String sheetName){
        //todo: 1. access the file 2. create the object of workbook 3. access the sheet from workbook 4. access the accordingly row, col or etc.
        try{
            fileInputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(fileInputStream);
            excelSheet = workBook.getSheet(sheetName);
            // get total no. of row's
            int ttlrows = excelSheet.getLastRowNum()+1; // +1 because they count start from 0 in java & in excel it was 1
            workBook.close();
            return  ttlrows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int getColCount(String fileName, String sheetName){
        //todo: 1. access the file 2. create the object of workbook 3. access the sheet from workbook 4. access the accordingly row, col or etc.
        try{
            fileInputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(fileInputStream);
            excelSheet = workBook.getSheet(sheetName);
            // get total no. of row's
            int ttlCols = excelSheet.getRow(0).getLastCellNum(); // +1 because they count start from 0 in java & in excel it was 1
            workBook.close();
            return  ttlCols;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
