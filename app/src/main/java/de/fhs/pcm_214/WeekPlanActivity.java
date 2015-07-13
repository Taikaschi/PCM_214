package de.fhs.pcm_214;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.LocalDate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class WeekPlanActivity extends Activity {

    private ListView listView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_plan);

        JodaTimeAndroid.init(this);
        ParseCSV parseCSV = new ParseCSV(this.getApplicationContext());
        FileInputStream in = null;
        FileOutputStream out = null;
        Timestamp today = new Timestamp(LocalDate.now());

        ListView listView = null;

        try {
            out = new FileOutputStream(new File("recipe.log"));
            in = new FileInputStream(new File("recipe.log"));

            int[] a = {3, 6, -1, -1};
            int[] b = {1, 6, 9, -1};
            int[] c = {3, 6, 2, 8};
            int[] d = {3, 6, 12, 17};
            int[] e = {3, 6, 4, -1};
            int[] f = {1, 2, 3, -1};
            int[] g = {15, 16, 17};
            int[] h = {3, 2, 1, -1};

            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 1), a);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 7), b);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 6), c);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 3), d);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 4), e);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 2), f);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 5), g);

            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 8), h);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 9), c);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 11), b);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 10), e);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 12), a);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 13), g);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 14), d);

            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 16), a);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 17), c);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 18), b);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 15), a);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 21), b);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 19), c);
            parseCSV.createEntry(this.getApplicationContext(), in, out, new Timestamp(2015, 7, 20), d);


            ArrayList<Day> list_week = parseCSV.getWeek(in, new Timestamp(today.getMonday()));

            DayAdapter week = new DayAdapter(this.getApplicationContext(), R.layout.list_view_item_row, list_week);


            listView.setAdapter(week);

            Calendar calendar = Calendar.getInstance();
            TextView textView = (TextView) findViewById(R.id.txtHeader);
            textView.setText("Kalenderwoche " + calendar.get(Calendar.WEEK_OF_YEAR));

            TextView textView1 = (TextView) findViewById(R.id.txtFooder);
            textView1.setText("Datum: " + calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR));
            View header = (View) getLayoutInflater().inflate(R.layout.list_view_header_row, null);
            listView.addHeaderView(header);

            View bottom = (View) getLayoutInflater().inflate(R.layout.list_view_bottom_row, null);
            listView.addFooterView(bottom);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), DayPlanActivity.class);
                    intent.putExtra("DAY", new Timestamp().getDate().plusDays(i).toString());
                    startActivity(intent);
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}