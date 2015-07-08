package de.fhs.pcm_214;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ParseCSV {


    private File cacheDir;
    private Context fileContext;

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


    //
    //          angenommen wird folgendes Format:
    //          TIMESTAMP,[rezeptx, rezepty, rezeptz, rezepta]
    //          bsp.                anderes bsp.
    //          20150722,1,3,7,18   19991201,6,7,23
    //


    public void readCSVFile(FileInputStream fin) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] timestamp_recipes = line.split("\\,");

                int timestamp = Integer.parseInt(timestamp_recipes[0]);

                //for (int i = 1; i < timestamp_recipes.length; i++)

/////////////////// HIER MUSS NOCH WAS PASSIEREN !!!!!!!!!!!!!!!


            }
            reader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
        }

    }

    public boolean writeCSVFile(FileOutputStream fout, int Timestamp, String text) {
        OutputStreamWriter writer = null;
        try {

            text = Timestamp + text;
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