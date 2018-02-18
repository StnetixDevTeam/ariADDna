package com.stnetix.ariaddna.commonutils.datetime;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 03.02.17.
 */
public class DateTimeTest {


    DateTime dateTime;

    @Before
    public void setUp() {
        dateTime = new DateTime();
    }

    @Test
    public void getDateTime() {
        System.out.println(dateTime.getDateTime());
    }

    @Test
    public void getDate() throws Exception {
        System.out.println(dateTime.getDate());
    }

    @Test
    public void getTime() throws Exception {
        System.out.println(dateTime.getTime());
    }

    @Test
    public void setDateTime() throws Exception {
        dateTime.setDateTime(2016, 8, 21, 12, 30, 45);
        System.out.println(dateTime.getDateTime());
    }

    @Test
    public void setDate() throws Exception {
        dateTime.setDate(2010, 12, 30);
        System.out.println(dateTime.getDateTime());
    }

    @Test
    public void setTime() throws Exception {
        dateTime.setTime(21, 30, 0);
        System.out.println(dateTime.getDateTime());
    }
    @Test
    public void getTimeInMillisec() throws Exception {
        System.out.println(dateTime.getTimeInMillisec());
    }

    @Test
    public void getTimeInMillisecWithSetDateTime() throws Exception {
        System.out.println(dateTime.getTimeInMillisec(2016, 8, 21, 12, 30, 45));
    }
}