package de.fhs.pcm_214;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ParseCSV {


    private File cacheDir;
    private Context fileContext;
    ArrayList<Day> all = null;
    ArrayList<Day> week = null;

    BufferedReader reader = null;
    String line;
    Day tmp = null;

    public ParseCSV(Context fileContext) {
        this.fileContext = fileContext;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "FHSCSV");
        else
            cacheDir = new File(android.os.Environment.getDataDirectory(), "FHSCSV");
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }


    public void createEntry(FileOutputStream out, Timestamp timestamp, int[] recipes) {         //TODO: sicherstellen das die einträge chronologisch erfolgen
        OutputStreamWriter writer = null;
        String line = "";

        try {
            line = String.valueOf(timestamp) + ",";                     // Zeile zum schreiben zusammenbauen
            for (int i = 1; i != recipes.length; i++) {
                line = line + String.valueOf(recipes[i]) + ",";
            }
            line.substring(0, line.length() - 1);

            writer = new OutputStreamWriter(out);
            writer.write(line);
            writer.flush();
            writer.close();

        }
        catch (FileNotFoundException e) { Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException", Toast.LENGTH_LONG).show();}
        catch (IOException e) { Toast.makeText(ParseCSV.this.fileContext, "IOException", Toast.LENGTH_LONG).show(); }

    }

    public int[] readEntry(FileInputStream in, Timestamp timestamp) {
        String[] recipe_log = null;
        Timestamp timestamp2 = null;
        int[] recipes = null;

        try {

            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            while ((line = reader.readLine()) != null) {

                recipe_log = line.split("\\,");
                timestamp2 = new Timestamp(recipe_log[0]);
                recipes = new int[recipe_log.length - 1];

                if (timestamp.equals(timestamp2)) {

                    for (int i = 0; i != recipe_log.length; i++) {
                        recipes[i] = Integer.parseInt(recipe_log[i + 1]);
                    }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) { Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException", Toast.LENGTH_LONG).show();}
        catch (IOException e) { Toast.makeText(ParseCSV.this.fileContext, "IOException", Toast.LENGTH_LONG).show(); }

        return recipes;
    }

    public void updateEntry(FileInputStream in, FileOutputStream out, Timestamp timestamp, int[] recipes) {             //TODO

    }

    public void deleteEntry(FileInputStream in, FileOutputStream out, Timestamp timestamp) {                            //TODO

    }
/*
    public int[] getWholeList(FileInputStream in) {
        String[] recipe_log = null;
        Timestamp timestamp2 = null;
        int[] recipes = null;

        try {

            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            while ((line = reader.readLine()) != null) {

                recipe_log = line.split("\\,");
                timestamp2 = new Timestamp(recipe_log[0]);
                recipes = new int[recipe_log.length - 1];

                for (int i = 0; i != recipe_log.length; i++) {
                    recipes[i] = Integer.parseInt(recipe_log[i + 1]);
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) { Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException", Toast.LENGTH_LONG).show();}
        catch (IOException e) { Toast.makeText(ParseCSV.this.fileContext, "IOException", Toast.LENGTH_LONG).show(); }

        return recipes;
    }
*/


    public Day[] getWeek(FileInputStream in, Timestamp monday) {
        Day[] week = new Day[7];

        for (int i = 0; i != week.length; i++) {
            week[i].setTimestamp(new Timestamp(monday.getYear(),monday.getMonth(),monday.getDay()+i));
            week[i].setRecipes(readEntry(in,new Timestamp(monday.getYear(),monday.getMonth(),monday.getDay()+i)));
        };

        return week;
    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

    /*
    public File getFile(String fileName) {
        File file = new File(cacheDir, fileName);
        return file;
    }
*/
}