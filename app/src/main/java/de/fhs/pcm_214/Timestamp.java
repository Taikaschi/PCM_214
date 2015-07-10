package de.fhs.pcm_214;

import java.util.Calendar;



/**
 * Created by soma on 09.07.15.
 */
public class Timestamp {

    private Calendar calendar;

    private int day;
    private int month;
    private int year;

    private final String[] days = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};


    public Timestamp() {}

    public Timestamp(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Timestamp(String yyyymmdd) {
        this.day = Integer.parseInt(getDay(yyyymmdd));
        this.month = Integer.parseInt(getMonth(yyyymmdd));
        this.year = Integer.parseInt(getYear(yyyymmdd));
    }

    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }

    public String getYear(String yyyymmdd) { return yyyymmdd.substring(0,4); }
    public String getMonth(String yyyymmdd) { return yyyymmdd.substring(4,6); }
    public String getDay(String yyyymmdd) { return yyyymmdd.substring(6,8); }

    public String getTimestampToday() {
        calendar = Calendar.getInstance();
        int today = (calendar.get(Calendar.YEAR) * 10000) + ((calendar.get(Calendar.MONTH) + 1) * 100) +(calendar.get(Calendar.DAY_OF_MONTH));
        return(String.valueOf(today));
    }

    // getTimestampWithOffset(-9); --> Heute - 9 Tage
    // getTimestampWithOffset(17); --> Heute + 17 Tage
    public String getTimestampWithOffset(int days) {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        int daywithoffset = (calendar.get(Calendar.YEAR) * 10000) + ((calendar.get(Calendar.MONTH) + 1) * 100) + (calendar.get(Calendar.DAY_OF_MONTH));
        return(String.valueOf(daywithoffset));
    }

    public String getNextWeek() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);                                                                                                         // +7
        int daywithoffset = (calendar.get(Calendar.YEAR) * 10000) + ((calendar.get(Calendar.MONTH) + 1) * 100) + (calendar.get(Calendar.DAY_OF_MONTH));
        return (String.valueOf(daywithoffset));
    }

    public String getLastWeek() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);                                                                                                        //-7
        int daywithoffset = (calendar.get(Calendar.YEAR) * 10000) + ((calendar.get(Calendar.MONTH) + 1) * 100) + (calendar.get(Calendar.DAY_OF_MONTH));
        return (String.valueOf(daywithoffset));
    }

    public String getFullDate() {
        calendar = Calendar.getInstance();

        String FullDate;

        FullDate = days[this.getDay()] + ", " + getDay() + "." + getMonth() + "." + getYear();

        return FullDate;
    }
}
