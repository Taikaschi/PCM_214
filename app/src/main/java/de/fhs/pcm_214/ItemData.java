package de.fhs.pcm_214;

import java.util.Calendar;

/**
 * Created by Sebastian on 08.07.2015.
 */
public class ItemData {
    Calendar calendar;
    String rezept;

    public int getDay() {
        calendar = Calendar.getInstance();
        return calendar.DAY_OF_MONTH;
    }


    public int getWeek() {
        calendar = Calendar.getInstance();
        return calendar.WEEK_OF_YEAR;
    }





}
