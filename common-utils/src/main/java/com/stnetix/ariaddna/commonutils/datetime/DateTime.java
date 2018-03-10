/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.commonutils.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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

    /**
     * Method returned current datetime instance in milliseconds from the start of ERA (1970-01-01)
     * @return long*/
    public long getTimeInMillisec() {
        return dateTime.atZone(ZoneId.of("UTC+0")).toInstant().toEpochMilli();
    }

    /**
     * Method returned current datetime instance in milliseconds from the start of ERA (1970-01-01)
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
    public long getTimeInMillisec(int year, int month, int day, int hour, int minute, int second) {
        setDateTime(year, month, day, hour, minute, second);
        return getTimeInMillisec();
    }

    @Override
    public String toString() {
        return getDateTime();
    }
}
