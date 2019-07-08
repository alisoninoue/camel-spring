package br.com.camelspring.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTypeConverter {

    public static LocalDate parseDate(String xmlGregorianCalendar) {
        return LocalDate.parse(xmlGregorianCalendar, DateTimeFormatter.ISO_DATE);
    }

    public static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static LocalDateTime parseDateTime(String xmlGregorianCalendar) {
        return LocalDateTime.parse(xmlGregorianCalendar, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String printDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }
}