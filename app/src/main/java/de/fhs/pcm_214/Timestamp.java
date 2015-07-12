package de.fhs.pcm_214;


import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Timestamp implements Comparable<Timestamp>{

    private Calendar calendar;

    private int day;
    private int month;              // 0-11
    private int year;
    private String name;


    private String yyyymmdd;

    public Timestamp() {
        calendar = Calendar.getInstance();

    }

    public Timestamp(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
        yyyymmdd = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
    }

    public Timestamp(String yyyymmdd) {
        this.day = Integer.parseInt(getDay(yyyymmdd));
        this.month = Integer.parseInt(getMonth(yyyymmdd));
        this.year = Integer.parseInt(getYear(yyyymmdd));
    }

    public String getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(String yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
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

    public String getTimestamp() {
        return this.yyyymmdd;
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

    public String getDateName() {
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("de"));
        String weekdays[] = dfs.getWeekdays();
        calendar.set(this.getYear(), this.getMonth(), this.getDay());
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        name = weekdays[d];

        return name;
    }

    public String getDateName(Timestamp timestamp) {

        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("de"));
        String weekdays[] = dfs.getWeekdays();
        calendar.set(timestamp.getYear(), timestamp.getMonth(), timestamp.getDay());
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        name = weekdays[d];

        return name;
    }

    public String getFullDate() {
        calendar = Calendar.getInstance();
        String FullDate;
        //FullDate = getDateName() + ", " + getDay() + "." + getMonth() + "." + getYear();
        FullDate = getDay() + "." + getMonth() + "." + getYear();


        return FullDate;
    }

    public Timestamp getMonday() {      // gibt den Montag, der Woche zurück, in der wir uns befinden

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        this.setYyyymmdd(String.valueOf(calendar.getTime().getYear()) + String.valueOf(calendar.getTime().getMonth()) + String.valueOf(calendar.getTime().getDay()));
        return this;
    }


    @Override
    public boolean equals(Object o) {
        //http://stackoverflow.com/questions/185937/overriding-the-java-equals-method-quirk
        Timestamp timestamp = (Timestamp) o;
        if (timestamp == null) return false;
        if (this.getDay() == timestamp.getDay() && this.getMonth() == timestamp.getMonth() && this.getYear() == timestamp.getYear()) return true;
        else return false;
    }

    @Override
    public int compareTo(Timestamp another) {               // wird benötigt für array.sort()
        if (this.equals(another))
            return 0;

        if (Integer.parseInt(this.getYyyymmdd()) < Integer.parseInt(another.getYyyymmdd()))
            return -1;

        if (Integer.parseInt(this.getYyyymmdd()) > Integer.parseInt(another.getYyyymmdd()))
            return 1;

        return 23;
    }
}
