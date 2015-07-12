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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ParseCSV {


    private File cacheDir;
    private Context fileContext;
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


    public void writeLine(FileOutputStream out, String line) {
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

    public void createEntry(FileInputStream in, FileOutputStream out, Timestamp timestamp, int[] recipes) {
        OutputStreamWriter writer = null;
        String line = "";
        ArrayList<Day> recipe_log = null;

        recipe_log = getWholeRecipeLogAsArrayListDay(in);               //ganze Datei einlesen in arraylist:day

        line = String.valueOf(timestamp) + ",";                     // Zeile zum schreiben zusammenbauen
        for (int i = 1; i != recipes.length; i++) {
            line = line + String.valueOf(recipes[i]) + ",";
        }
        line.substring(0, line.length() - 1);

        recipe_log.add(new Day(line));

        Collections.sort(recipe_log, new Comparator<Day>() {                                // hoffentlich funktioniert das
            @Override
            public int compare(Day lhs, Day rhs) {
                if (lhs.equals(rhs))
                    return 0;

                if (Integer.parseInt(lhs.getTimestamp().getYyyymmdd()) < Integer.parseInt(rhs.getTimestamp().getYyyymmdd()))
                    return -1;

                if (Integer.parseInt(lhs.getTimestamp().getYyyymmdd()) > Integer.parseInt(rhs.getTimestamp().getYyyymmdd()))
                    return 1;

                return 23;
            }
        });

        for (Day item: recipe_log) {
            line = String.valueOf(item.getTimestamp()) + ",";
            for (int i = 0; i < item.getRecipes().length; i++) {
                line += String.valueOf(item.getRecipes()[i]) + ",";
            }
            line.substring(0, line.length() - 1);

            writeLine(out,line);
        }

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
        } catch (FileNotFoundException e) {
            Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(ParseCSV.this.fileContext, "IOException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

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


    public ArrayList<Day> getWeek(FileInputStream in, Timestamp monday) {
        ArrayList<Day> list = new ArrayList<>(7);



        for (int i = 0; i < 7; i++) {
            list.add(new Day(new Timestamp(monday.getTimestampWithOffset(i)), readEntry(in,new Timestamp(new Timestamp().getTimestampWithOffset(i)))));
        }

        return list;
    }


    public String[] getWholeRecipeLogAsArray(FileInputStream in) {

        String[] recipe_log = null;
        String line = null;

        try {

            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int i = 0;
            while ((line = reader.readLine()) != null) {
                recipe_log[i] = line;
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(ParseCSV.this.fileContext, "IOException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return recipe_log;
    }

    public ArrayList<String> getWholeRecipeLogAsArrayListString(FileInputStream in) {
        ArrayList<String> recipe_log = null;
        String line = null;

        try {

            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            while ((line = reader.readLine()) != null) {
                recipe_log.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(ParseCSV.this.fileContext, "FileNotFoundException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(ParseCSV.this.fileContext, "IOException:" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return recipe_log;
    }


    public ArrayList<Day> getWholeRecipeLogAsArrayListDay(FileInputStream in) {
        ArrayList<Day> recipe_log = null;
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

    /*
    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }

    public File getFile(String fileName) {
        File file = new File(cacheDir, fileName);
        return file;
    }
*/
}