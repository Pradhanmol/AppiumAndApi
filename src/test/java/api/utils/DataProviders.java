package api.utils;

import org.fos.api.utils.ReadExcelFile;
import org.testng.annotations.DataProvider;

public class DataProviders {
/*
 Todo
    *   1. In data provider first annotate the function using the @dataprovider which for knowing which type of data provide this function
    *  2. Then create the function of getting the data & then access the data from there using below steps
    *      a. access the file path
    *      b. get the count of row & col & traverse them on excel & remember one thing return 2d array

*/

    @DataProvider(name="AllData")
    public static String[][] getAllDataFromLoanExcel(){
        String fname  = System.getProperty("user.dir")+"src/main/resources/testData.xlsx";
        int ttlRowCount = ReadExcelFile.getRowCount(fname,"sheet1");
        int ttlColCount = ReadExcelFile.getColCount(fname, "Sheet");
        String loanData[][] = new String[ttlRowCount-1][ttlColCount];
        for (int rowNo = 1; rowNo<ttlRowCount; rowNo++){
            for (int colNo = 0; colNo <=ttlColCount; colNo++) {
                 loanData[rowNo-1][colNo] = ReadExcelFile.getCellValue(fname,"Sheet1",rowNo,colNo);
            }
        }
        return loanData;
    }
    @DataProvider(name="allLoanId")
    public String[] getOnlyLoanId(){
        String fname = System.getProperty("user.dir")+"src/main/resources/testData.xlsx";
        int ttlRowCount = ReadExcelFile.getRowCount(fname,"Sheet1");
        String loanid[] = new String[ttlRowCount-1];
        for (int rowNo = 0; rowNo < ttlRowCount; rowNo++) {
            loanid[rowNo] = ReadExcelFile.getCellValue(fname,"Sheet1",rowNo,1);
        }
        return loanid;
    }
}
