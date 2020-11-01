import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class WorkWithDate {

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate parseStringToDate(String str){
        LocalDate result = null;
        ArrayList<String> formatItems = new ArrayList<>(Arrays.asList(
                "dd.MM.yyyy",
                "dd.MM.yy",
                "dd/MM/yyyy",
                "dd/MM/yy",
                "dd-MM-yyyy",
                "dd-MM-yy")
        );
        for (String item : formatItems) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(item);
                result = LocalDate.parse(str, format);
                System.out.println(item + " is correct format");
            } catch (Exception e) {
                System.out.println(item + " is incorrect format");
            }
        }
        if (result == null){
            throw new NullPointerException("Incorrect format");
        }
        return result;
    }


    public static int calculateAge (LocalDate startDate, LocalDate finishDate){
        if(startDate == null || finishDate == null){
            return -1;
        }
        return Period.between(startDate, finishDate).getYears();
    }
}
