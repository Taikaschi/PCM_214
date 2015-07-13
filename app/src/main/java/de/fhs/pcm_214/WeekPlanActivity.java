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
        
        ParseCSV parseCSV = null;

        FileInputStream in = null;



        try {
            FileOutputStream out = new FileOutputStream(new File("recipe.log"));
            int[] a = {3,6,-1,-1};
            int[] b = {1,6,9,-1};
            int[] c = {3,6,2,8};
            int[] d = {3,6,12,17};
            int[] e = {3,6,4,-1};
            int[] f = {1,2,3,-1};
            int[] g = {15,16,17};
            int[] h = {3,2,1,-1};






            in = new FileInputStream(new File("recipe.log"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        parseCSV = new ParseCSV(getApplicationContext());

        Timestamp timestamp = new Timestamp(LocalDate.now());
        ArrayList<Day> list = parseCSV.getWeek(in, timestamp);


        WeekAdapter adapter = new WeekAdapter(this, R.layout.list_view_item_row, list);


        listView1 = (ListView) findViewById(R.id.listView1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DayPlanActivity.class);
                intent.putExtra("DAY", new Timestamp().getDate().plusDays(i).toString());
                startActivity(intent);
            }
        });


        View header = (View) getLayoutInflater().inflate(R.layout.list_view_header_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);

        View bottom = (View) getLayoutInflater().inflate(R.layout.list_view_bottom_row, null);
        listView1.addFooterView(bottom);

        Calendar calendar = Calendar.getInstance();
        TextView textView = (TextView)findViewById(R.id.txtHeader);
        textView.setText("Kalenderwoche " + calendar.get(Calendar.WEEK_OF_YEAR));

        TextView textView1 = (TextView)findViewById(R.id.txtFooder);
        textView1.setText("Datum: " + calendar.get(Calendar.DAY_OF_MONTH) + "." +
                (calendar.get(Calendar.MONTH)+1) +"."+ calendar.get(Calendar.YEAR));

    }
}