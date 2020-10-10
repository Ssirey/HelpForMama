import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Handler {
    HashMap<Integer, Integer> boys;
    HashMap<Integer, Integer> girls;
    ArrayList<String> errArr;

    Integer[][] result;

    Handler(){
        boys = new HashMap<>();
        girls = new HashMap<>();
        errArr = new ArrayList<>();
        result = new Integer[][] {};
    }

    public HashMap<Integer, Integer> getBoys() {
        return boys;
    }
    public HashMap<Integer, Integer> getGirls() {
        return girls;
    }
    public ArrayList<String> getErrArr() {
        return errArr;
    }

    public void countAges(Sheet sourceSheet){
        int rowSize = sourceSheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowSize; i++){
            Cell cell = sourceSheet.getRow(i).getCell(2);

            //Day of Birth
            LocalDate dobDate = WorkWithDate.parseStringToDate(
                    cell.getStringCellValue().replaceAll("(^\\h*)|(\\h*$)",""),
                    "dd.MM.yy"
            );
            //Current day
            LocalDate currentDate = WorkWithDate.convertToLocalDateViaInstant(new Date());

            int age = WorkWithDate.calculateAge(dobDate, currentDate);
            String sex = sourceSheet.getRow(i).getCell(6).getStringCellValue();

            if ("м".equals(sex) || "M".equals(sex)){
                if(boys.get(age) == null){
                    boys.put(age, 1);
                } else {
                    boys.put(age, boys.get(age)+1);
                }
            } else if ("д".equals(sex) || "Д".equals(sex) || "ж".equals(sex) || "Ж".equals(sex)){
                if(girls.get(age) == null){
                    girls.put(age, 1);
                } else {
                    girls.put(age, girls.get(age)+1);
                }
            } else {
                System.out.println("Неизвестный пол! Строка номер: " + i);
                errArr.add(sourceSheet.getRow(i).getCell(1).getStringCellValue());
            }


        }
    }

    private void addAge(Integer age, ArrayList<Integer> arr){
        if(arr.indexOf(age) < 0){
            arr.add(age);
        }
    }

    private void parseAge(int age, ArrayList<Integer[]> result){
        Integer[] resArr = new Integer[3];
        resArr[0] = age;
        resArr[1] = boys.get(age) != null ? boys.get(age) : 0;
        resArr[2] = girls.get(age) != null ? girls.get(age) : 0;
        result.add(resArr);
    }

    public void calculate(File file, JBetterSimpleTable table){
        Workbook wb = OpenAndSave.reedWorkbook(file);
        Sheet sh = OpenAndSave.getFirstSourceSheet(wb);
        countAges(sh);

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        ArrayList<Integer> boysAgesList = new ArrayList<>();
        ArrayList<Integer> girlsAgesList = new ArrayList<>();
        Collections.addAll(boysAgesList, boys.keySet().toArray(new Integer[boys.size()]));
        Collections.addAll(girlsAgesList, girls.keySet().toArray(new Integer[girls.size()]));

        ArrayList<Integer> ages = new ArrayList<>();

        boysAgesList.forEach(age -> addAge(age, ages));
        girlsAgesList.forEach(age -> addAge(age, ages));
        Collections.sort(ages);
        ArrayList<Integer[]> result = new ArrayList<>();
        ages.forEach(age -> parseAge(age, result));

        //clean table
        for (int i = model.getRowCount()-1; i >= 0; i--){
            model.removeRow(i);
        }
        //add new rows to table
        result.forEach(row -> model.addRow(row));
    }
}
