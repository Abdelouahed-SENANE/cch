package ma.youcode.cch.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DateTimeParser {


    public static LocalDate parseDate(String stringDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(stringDate.trim(), formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
