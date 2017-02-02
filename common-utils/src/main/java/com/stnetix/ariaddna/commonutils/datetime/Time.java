package com.stnetix.ariaddna.commonutils.datetime;

import com.stnetix.ariaddna.commonutils.datetime.exeption.DateTimeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by alexkotov on 02.02.17.
 */
public class Time {
    private LocalTime time;

    public Time() {
        time = LocalTime.now();
    }

    public Time(int hour, int minute, int second) throws DateTimeException {
        checkValue(hour,TimeFields.HOUR);
        checkValue(minute,TimeFields.MINUTE);
        checkValue(second,TimeFields.SECOND);
        time = LocalTime.of(hour, minute, second);
    }

    public void setTime(Time time) {
        this.time = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
    }

    public void setTime(int hour, int minute, int second) throws DateTimeException {
        checkValue(hour,TimeFields.HOUR);
        checkValue(minute,TimeFields.MINUTE);
        checkValue(second,TimeFields.SECOND);
        this.time = LocalTime.of(hour, minute, second);
    }

    private void checkValue(int field, TimeFields timeField) throws DateTimeException {
        switch (timeField){
            case HOUR:{
                if(field<0||field>23) throw new DateTimeException(" ");
            }
            case MINUTE:{
                if(field<0||field>59) throw new DateTimeException(" ");
            }
            case SECOND:{
                if(field<0||field>59) throw new DateTimeException(" ");
            }
        }
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("HH.mm.ss", Locale.ENGLISH));
    }

    private int getSecond() {
        return time.getSecond();
    }

    private int getMinute() {
        return time.getMinute();
    }

    private int getHour() {
        return time.getHour();
    }

    private enum TimeFields{
        HOUR,MINUTE,SECOND
    }




}
