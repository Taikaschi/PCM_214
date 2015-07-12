package de.fhs.pcm_214;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Calendar;


public class WeekPlanActivity extends Activity {

    private ListView listView1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_plan);

        ParseCSV parseCSV = null;

        String[] rezepte = {
                "Penne-Hähnchen-Auflauf mit Mozzarella-Brot-Kruste",
                "Italienischer Nudelauflauf mit Tomaten und Bacon",
                "Nudel-Gemüse-Auflauf",
                "Nudel-Auflauf mit Blumenkohl und Spinat zu Tomatensoße",
                "Makkaroni-Bratwurst-Auflauf mit Möhren und Champignons",
                "Kohlrabi-Kartoffelsuppe mit Räucherlachs und Kresse",
                "Cremige Apfel-Sellerie-Suppe",
                "Hühnersuppe mit Nudeln",
                "Portugiesischer Maisgrieß-Kuchen",
                "Luftiger Himbeerkuchen",
                "Kokos-Streuselkuchen mit Ananas",
                "Kartoffel-Tortilla",
                "Portugiesische Kartoffelsuppe mit Chorizo",
                "Kartoffelpuffer mit Apfelmus",
                "Chicken-Burger mit Mangosalsa und Guacamole",
                "Zwiebelringe in Bierteig",
                "Schweinebraten-Burger mit Texassoße",
                "Backkartoffelsalat mit Rucolapesto",
                "Klassischer Kartoffelsalat mit Brühe",
                "Kartoffelsalat mit gebratener Zucchini, Paprika und Kichererbsen",
                "Kartoffel-Kräutersalat"};

        Week week_data[] = new Week[]
                {
                        new Week(R.drawable.ball_red, "Montag", "06.07.2015"),
                        new Week(R.drawable.ball_red, "Dienstag", "07.07.2015"),
                        new Week(R.drawable.ball_red, "Mittwoch", "08.07.2015"),
                        new Week(R.drawable.ball_red, "Donnerstag", "09.07.2015"),
                        new Week(R.drawable.ball_red, "Freitag", "10.07.2015"),
                        new Week(R.drawable.ball_red, "Samstag", "11.07.2015"),
                        new Week(R.drawable.ball_red, "Sonntag", "12.07.2015")
                };

        WeekAdapter adapter = new WeekAdapter(this,
                R.layout.list_view_item_row, week_data);


        listView1 = (ListView) findViewById(R.id.listView1);

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