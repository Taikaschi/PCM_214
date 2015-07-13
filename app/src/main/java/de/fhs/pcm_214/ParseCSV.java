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
import java.util.Collections;


public class ParseCSV {


    private File cacheDir;
    private Context fileContext;

    static final int[] empty_recipes = {-1,-1,-1,-1};

    String line;
    Day tmp = null;

    public ParseCSV(Context fileContext) {
        initDirectory(fileContext);
    }

    private void initDirectory(Context fileContext) {
        this.fileContext = fileContext;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "FHSCSV");
        else
            cacheDir = new File(android.os.Environment.getDataDirectory(), "FHSCSV");
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }


    private void writeLine(FileOutputStream out, String line) {
        OutputStreamWriter writer = null;

        try {
            writer = new OutputStreamWriter(out);
            writer.write(line);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Toast.makeText(ParseCSV.this.fileContext, "IOException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void createEntry(Context fileContext, FileInputStream in, FileOutputStream out, Timestamp timestamp, int[] recipes) {
        this.fileContext = fileContext;
        OutputStreamWriter writer = null;
        String line = "";
        ArrayList<Day> recipe_log =  getWholeRecipeLogAsArrayListDay(in);                       //ganze Datei einlesen in arraylist:day

        line = timestamp.getDateString() + ",";                                 // Zeile zum schreiben zusammenbauen
        for (int i = 0; i != recipes.length; i++) {
            line = line + Integer.toString(recipes[i]) + ",";
        }
        line = line.substring(0, line.length() - 1);

        Day new_day = new Day(line);
        recipe_log.add(new_day);                                          // zeile zu arraylist hinzufügen

        Collections.sort(recipe_log);                                           // array list nach zeit sortieren

        clear();
        initDirectory(fileContext);

        for (Day item : recipe_log) {
            line = item.getTimestamp() + ",";
            int[] item_array = item.getRecipes();

            item.setRecipes();


            writeLine(out, line);
        }

    }

    public int[] readEntry(FileInputStream in, Timestamp timestamp) {
        ArrayList<Day> recipe_log = null;
        int[] recipes = null;

        recipe_log = getWholeRecipeLogAsArrayListDay(in);

        for (Day item : recipe_log) {
            if (item.getTimestamp().getDateString().equals(timestamp.getDateString())) ;
            {
                recipes = item.getRecipes();
            }
        }
        return recipes;
    }

    public void updateEntry(Context context, FileInputStream in, FileOutputStream out, Timestamp timestamp, int[] recipes) {
        BufferedReader reader = null;
        ArrayList<Day> recipe_log = null;

        recipe_log = getWholeRecipeLogAsArrayListDay(in);

        for (Day item : recipe_log) {
            if (item.getTimestamp().getDateString().equals(timestamp.getDateString())) ;
            {
                createEntry(context, in, out, timestamp, recipes);
            }
        }

    }

    public void deleteEntry(Context context,FileInputStream in, FileOutputStream out, Timestamp timestamp) {
        BufferedReader reader = null;
        ArrayList<Day> recipe_log = null;


        recipe_log = getWholeRecipeLogAsArrayListDay(in);

        for (Day item : recipe_log) {
            if (item.getTimestamp().getDateString().equals(timestamp.getDateString())) ;
            {
                createEntry(context, in, out, timestamp, empty_recipes);
            }
        }
    }



    public ArrayList<Day> getWeek(FileInputStream in, Timestamp monday) {
        ArrayList<Day> list = new ArrayList<>(7);

        for (int i = 0; i < 7; i++) {
            Timestamp offset = new Timestamp(monday.getDate().plusDays(i));
            Day day = new Day(monday.getDate().plusDays(i).toString());
            day.setRecipes(readEntry(in, offset));

            list.add(day);
        }

        return list;
    }

    private ArrayList<Day> getWholeRecipeLogAsArrayListDay(FileInputStream in) {
        BufferedReader reader = null;
        ArrayList<Day> recipe_log = new ArrayList<>();
        String line = null;

        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            while ((line = reader.readLine()) != null) {
                recipe_log.add(new Day(line));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(ParseCSV.this.fileContext, "IOException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return recipe_log;
    }


    private void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

    private File getFile(String fileName) {
        File file = new File(cacheDir, fileName);
        return file;
    }


}