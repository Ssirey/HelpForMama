import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

public class ResultBook {
    private XSSFWorkbook book;

    private ResultBook(){
        XSSFWorkbook resultBook = new XSSFWorkbook();

        Sheet sheet = resultBook.createSheet();
        //title row
        sheet.createRow(0)
                .createCell(0)
                .setCellValue(new Date());

        //pointer row
        Row numRow = sheet.createRow(1);
        numRow.createCell(0).setCellValue("Возраст");
        numRow.createCell(1).setCellValue("Мальчик");
        numRow.createCell(2).setCellValue("Девочка");

        for (int i = numRow.getRowNum() + 1; i <= 18; i++) {
            Row newRow = sheet.createRow(i);
            newRow.createCell(0).setCellValue(i);
            newRow.createCell(1).setCellValue(0);
            newRow.createCell(2).setCellValue(0);
        }
    }

    public static XSSFWorkbook createAndReturn(){
        return new ResultBook().book;
    }
}
