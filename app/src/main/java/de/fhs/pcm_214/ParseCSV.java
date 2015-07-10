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

    public ParseCSV(Context fileContext) {
        this.fileContext = fileContext;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "FHSCSV");
        else
            cacheDir = new File(android.os.Environment.getDataDirectory(), "FHSCSV");
        if (!cacheDir.exists())
            cacheDir.mkdirs();
    }

/*
    public File getFile(String fileName) {
        File file = new File(cacheDir, fileName);
        return file;
    }
*/



    public void readCSVFile(FileInputStream fin) {
        BufferedReader reader = null;
        String line;
        Day tmp = null;

        try {
            reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));

            while ((line = reader.readLine()) != null) {                        // Zeilenweise einlesen

                String[] recipe_log = line.split("\\,");                 // eingelesenen String bei Kommas aufspllitten und teilstrings als elemente ins array
                Timestamp timestamp =  new Timestamp(recipe_log[0]);
                int[] recipes = new int[recipe_log.length-1];            // recipe-array größe = gesamt - timestamp

                for (int i = 0; i != recipe_log.length; i++) {           // die restlichen werte herausholen ...
                    recipes[i] = Integer.parseInt(recipe_log[i+1]);      // ...umwandeln und neu verpacken
                }

                tmp.setRecipes(recipes);                                        // objekt erzeugt
                tmp.setTimestamp(timestamp);
                all.add(tmp);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(ParseCSV.this.fileContext,"FileNotFoundException",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
        }

    }



    public boolean writeCSVFile(FileOutputStream fout, int Timestamp, int[] recipes) {

        OutputStreamWriter writer = null;
        String text = "";

        try {
            text = String.valueOf(Timestamp) + ",";             // TIMESTAMP,

            for (int i = 1; i != recipes.length; i++) {         //x,y,z,
                text = text + String.valueOf(recipes[i]) + ",";
            }
            text.substring(0,text.length()-1);                  // entfernt letztes komma

            writer = new OutputStreamWriter(fout);
            writer.write(text);
            writer.flush();
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }
}