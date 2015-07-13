package de.fhs.pcm_214;

import org.joda.time.LocalDate;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class Timestamp implements Comparable<Timestamp>{


    Calendar calendar;


    LocalDate date;

    public Timestamp() {
        super();
        this.date = LocalDate.now();
    }

    public Timestamp(int y, int m, int d) {
        this.date = LocalDate.fromCalendarFields(new GregorianCalendar(y, m, d));
    }

    public Timestamp(LocalDate localDate) {
        this.date = localDate;
    }

    public Timestamp(String timestamp) {
        String[] ymd = timestamp.split("-");
        this.date = new LocalDate(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]) );
    }

    public String getDateString() {
        return this.date.toString();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDate getMonday() {
        this.date = this.date.minusDays(this.date.getDayOfWeek() - 1);
        return this.date;
    }

    public void setLastWeek() {
        this.date = this.date.minusDays(7);
    }

    public void setNextWeek() {
        this.date = this.date.plusDays(7);
    }

    public String getDateName(Timestamp timestamp) {

        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("de"));
        String weekdays[] = dfs.getWeekdays();

        String name = weekdays[this.date.getDayOfWeek()];

        return name;
    }

    @Override
    public int compareTo(Timestamp another) {
        if (this.getDate().isAfter(another.getDate())) {
            return 1;
        }

        if (this.getDate().isAfter(another.getDate())) {
            return -1;
        }

        if (this.getDate().isEqual(another.getDate())) {
            return 0;
        }

        return 23;
    }
}
