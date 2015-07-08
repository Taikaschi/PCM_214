package de.fhs.pcm_214;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wp_test = (Button) findViewById(R.id.wp_test);
        wp_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent WeekPlanIntent = new Intent();
                WeekPlanIntent.setClass(MainActivity.this, WeekPlanActivity.class);
                startActivity(WeekPlanIntent);
            }
        });

        Button dp_test = (Button) findViewById(R.id.dp_test);
        dp_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DayPlanIntent = new Intent();
                DayPlanIntent.setClass(MainActivity.this, DayPlanActivity.class);
                startActivity(DayPlanIntent);
            }
        });

        Button katalog_test = (Button) findViewById(R.id.katalog_test);
        katalog_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent KatalogIntent = new Intent();
                KatalogIntent.setClass(MainActivity.this, MainActivity_Katalog.class);
                startActivity(KatalogIntent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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