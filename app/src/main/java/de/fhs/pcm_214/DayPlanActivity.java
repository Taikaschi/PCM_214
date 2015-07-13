package de.fhs.pcm_214;

import android.app.Activity;
import android.content.Intent;
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
import java.util.ArrayList;

public class DayPlanActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_plan);
        //JodaTimeAndroid.init(this);

        ArrayList<TextView> recipeArray =new ArrayList<TextView>();
        recipeArray.add((TextView) findViewById(R.id.textView_rcp1));
        recipeArray.add((TextView) findViewById(R.id.textView_rcp2));
        recipeArray.add((TextView) findViewById(R.id.textView_rcp3));
        recipeArray.add((TextView) findViewById(R.id.textView_rcp4));

        Intent intent = this.getIntent();
        Recipes data = new Recipes();
        // Receiving the Data
        int[] receive_recipes = intent.getIntArrayExtra("result");

       // Displaying Received data

        for (int j = 0; j < receive_recipes.length; j++) {

            if(receive_recipes.length > 0){
                String temp = (data.getRecipes(receive_recipes[j]));
                recipeArray.get(j).setText(temp);}

        }


            /*Timestamp timestamp = new Timestamp(getIntent().getExtras().toString());


            ParseCSV parseCSV = new ParseCSV(getApplicationContext());


            FileInputStream in = openFileInput("recipe.log");
            int[] recipes = parseCSV.readEntry(in, timestamp);*/


           /* public void deleteEntry(FileInputStream in, FileOutputStream out, Timestamp timestamp) {                            //TODO

            }
            */

       /* } catch (FileNotFoundException e) {

        } catch (NullPointerException e) {
            Log.d("ASD", e.getLocalizedMessage());
        }

*/


        Button cancel = (Button) findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent();
                cancelIntent.setClass(DayPlanActivity.this, WeekPlanActivity.class);
                startActivity(cancelIntent);
            }
        });
        Button add = (Button) findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent();
                addIntent.setClass(DayPlanActivity.this, MainActivity_Katalog.class);
                startActivity(addIntent,savedInstanceState);
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
