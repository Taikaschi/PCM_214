package de.fhs.pcm_214;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.danlew.android.joda.JodaTimeAndroid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DayPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_plan);
        JodaTimeAndroid.init(this);


        try {
            TextView textView_rcp1 = (TextView) findViewById(R.id.textView_rcp1);
            TextView textView_rcp2 = (TextView) findViewById(R.id.textView_rcp2);
            TextView textView_rcp3 = (TextView) findViewById(R.id.textView_rcp3);
            TextView textView_rcp4 = (TextView) findViewById(R.id.textView_rcp4);
            TextView[] array = {textView_rcp1, textView_rcp2, textView_rcp3, textView_rcp4};



            Timestamp timestamp = new Timestamp(getIntent().getExtras().toString());


            ParseCSV parseCSV = new ParseCSV(getApplicationContext());


            FileInputStream in = new FileInputStream(new File("recipe.log"));
            int[] recipes = parseCSV.readEntry(in, timestamp);


            Recipes data = new Recipes();

            for (int i = 0; i < recipes.length; i++) {
                array[i].setText(data.getRecipes(recipes[i]));
            }

           /* public void deleteEntry(FileInputStream in, FileOutputStream out, Timestamp timestamp) {                            //TODO

            }
            */

        } catch (FileNotFoundException e) {

        } catch (NullPointerException e) {
            Log.d("ASD", e.getLocalizedMessage());
        }


        //Intent i = getIntent();
        // Receiving the Data
        //int[] recipe = (int) i.getIntegerArrayListExtra("result");

        // Displaying Received data
        //textView_rcp1.setText(recipe);


        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent();
                cancelIntent.setClass(DayPlanActivity.this, WeekPlanActivity.class);
                startActivity(cancelIntent);
            }
        });

        Button okay = (Button) findViewById(R.id.ok_button);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent okIntent = new Intent();
                okIntent.setClass(DayPlanActivity.this, WeekPlanActivity.class);
                startActivity(okIntent);
                Toast.makeText(getApplicationContext(),
                        "Eingaben wurden übernommen", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
