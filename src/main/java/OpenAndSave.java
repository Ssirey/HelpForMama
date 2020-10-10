import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;


public class OpenAndSave {
    public static XSSFWorkbook reedWorkbook(String fileName){
        try{
            OPCPackage pkg = OPCPackage.open(new File(fileName));
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
            return wb;
        }
        catch (Exception e){
            System.out.println("File: " + fileName + " wasn't found. " + e);
            return null;
        }
    }

    public static XSSFWorkbook reedWorkbook(File file){
        try {
            OPCPackage pkg = OPCPackage.open(file);
            XSSFWorkbook wb = new XSSFWorkbook(pkg);
            return wb;
        }
        catch (Exception e){
            System.out.println("File: " + file + " wasn't found " + e);
            return null;
        }
    }

    public static Sheet getFirstSourceSheet(Workbook sourceBook){
        try{
            return sourceBook.getSheetAt(0);
        }
        catch (Exception e){
            System.out.println("Первого листа не существует");
            return sourceBook.createSheet();
        }
    }

    public static void writeWorkbook(XSSFWorkbook book, String fileName){
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            book.write(fileOut);
            fileOut.close();
        }
        catch (Exception e){
            System.out.println("Error while write in file: " + fileName + " and Error text: " + e);
        }
    }
}
