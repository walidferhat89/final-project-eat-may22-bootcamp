package reporting.utility;

import Base.commonApi;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class excelReader extends commonApi {
        XSSFWorkbook workbook;
        XSSFCell cell;
        FileInputStream excelFile;
        XSSFSheet sheet;
        String filePath;
        String sheetName;
        String value;

public excelReader(String filePath,String sheetName){
        this.filePath = utility.currentDir+"\\data\\amazoneData.xlsx";
        this.sheetName = "sheet1";
        }


public  String getDataFromCell(int row,int column) {
        value=null;
        try {
        excelFile = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(excelFile);
        cell = workbook.getSheet(sheetName).getRow(row).getCell(column);
        value = cell.getStringCellValue();
                excelFile.close();
        }
        catch (IOException e) {
        e.printStackTrace();
        }
        return value;
        }

public List<String> getAllColumnData(int rowStart, int column)  {
        List<String> columnData = new ArrayList<>();
        try {
        excelFile = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(excelFile);
        sheet = workbook.getSheet(sheetName);
        for (int i = rowStart; i <= sheet.getLastRowNum(); i++) {
                columnData.add(sheet.getRow(i).getCell(column).getStringCellValue());
        }
        excelFile.close();
        }
        catch (IOException e){
        e.printStackTrace();
        }

        return columnData;
        }


  public  String getDataForGivenKey(String header,String key)  {
        String value = null;
        int i =0;
        while (getDataFromCell(0,i)!= null){
                if (getDataFromCell(0,i).equalsIgnoreCase(header)){
                        for (int j =0; j < getAllColumnData(1,i).size() ; j++) {
                                if (getAllColumnData(1,i).get(j).equalsIgnoreCase(key)){
                                        value = getAllColumnData(1,i+1).get(j);
                                }
                         }
                break;
                }
          i++;
        }
        return value;
        }

        /*public static void main(String[] args) throws IOException {
                excelReader excelReader = new excelReader("C:\\Users\\walid\\IdeaProjects\\framework-selenium-practice\\data\\amazoneData.xlsx","sheet1");
                String data = excelReader.getDataForGivenKey("key","CreateNewAccountPageHeader");
                System.out.println(data);
        }*/
}
