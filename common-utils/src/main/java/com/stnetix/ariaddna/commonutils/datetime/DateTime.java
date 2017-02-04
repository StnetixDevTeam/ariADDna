package com.stnetix.ariaddna.commonutils.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * This class wrapped simple realisation of {@link LocalDateTime} class.
 */
public class DateTime {
    private LocalDateTime dateTime;

    /**
     * Empty constructor
     * */
    public DateTime() {
        dateTime = LocalDateTime.now();
    }

    /**
     * Parameterized constructor
     * @param year value range -999999999 to 999999999;
     * @param month value range 1 to 12;
     * @param day value range 1 to 31. In the default ISO calendar system, this has values from 1 to 31 in most months.
     * April, June, September, November have days from 1 to 30, while February has days
     * from 1 to 28, or 29 in a leap year.;
     * @param hour value range 0 to 23;
     * @param minute value range 0 to 59;
     * @param second value range 0 to 59;
     *
     * @throws java.time.DateTimeException if any param value is not valid
     * */
    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * Method return date and time in "yyyy.MM.dd HH.mm.ss" format
     * */
    public String getDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss", Locale.ENGLISH));
    }

    /**
     * Method return date in "yyyy.MM.dd" format
     * */
    public String getDate() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.ENGLISH));
    }

    /**
     * Method return time in "HH.mm.ss" format
     * */
    public String getTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("HH.mm.ss", Locale.ENGLISH));
    }

    /**
     * Method set date and time to this instance
     * @param year value range -999999999 to 999999999;
     * @param month value range 1 to 12;
     * @param day value range 1 to 31. In the default ISO calendar system, this has values from 1 to 31 in most months.
     * April, June, September, November have days from 1 to 30, while February has days
     * from 1 to 28, or 29 in a leap year.;
     * @param hour value range 0 to 23;
     * @param minute value range 0 to 59;
     * @param second value range 0 to 59;
     *
     * @throws java.time.DateTimeException if any param value is not valid
     * */
    public void setDateTime(int year, int month, int day, int hour, int minute, int second) {
        dateTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * Method set date and time to this instance
     * @param year value range -999999999 to 999999999;
     * @param month value range 1 to 12;
     * @param day value range 1 to 31. In the default ISO calendar system, this has values from 1 to 31 in most months.
     * April, June, September, November have days from 1 to 30, while February has days
     * from 1 to 28, or 29 in a leap year.;

     * @throws java.time.DateTimeException if any param value is not valid
     * */
    public void setDate(int year, int month, int day) {
        dateTime = LocalDateTime.of(LocalDate.of(year, month, day), dateTime.toLocalTime());
    }

    /**
     * Method set date and time to this instance
     * @param hour value range 0 to 23;
     * @param minute value range 0 to 59;
     * @param second value range 0 to 59;

     * @throws java.time.DateTimeException if any param value is not valid
     * */
    public void setTime(int hour, int minute, int second) {
        dateTime = LocalDateTime.of(dateTime.toLocalDate(), LocalTime.of(hour, minute, second));
    }

    @Override
    public String toString() {
        return getDateTime();
    }
}
