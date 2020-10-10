import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class WorkWithDate {
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate parseStringToDate(String str, String formate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formate);
        return LocalDate.parse(str, formatter);
    }

    public static int calculateAge (LocalDate startDate, LocalDate finishDate){
        if(startDate == null || finishDate == null){
            return -1;
        }
        return Period.between(startDate, finishDate).getYears();
    }
}
