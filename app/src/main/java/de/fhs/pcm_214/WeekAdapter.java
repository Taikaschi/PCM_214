package de.fhs.pcm_214;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Sebastian on 08.07.2015.
 */
public class WeekAdapter extends ArrayAdapter<Day> {
    Context context;
    int layoutResourceId;
    ArrayList<Day> data;
    FileInputStream in;


    ParseCSV parseCSV;
    public WeekAdapter(Context context, int layoutResourceId, ArrayList<Day> data) {
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeekHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new WeekHolder();


            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtDate = (TextView)row.findViewById(R.id.txtDate);
            row.setTag(holder);
        }
        else
        {
            holder = (WeekHolder)row.getTag();
        }

        Day day = getItem(position);

        holder.txtTitle.setText(day.getTimestamp().getDate().getDayOfMonth());

        holder.imgIcon.setImageResource(day.icon);
        holder.txtDate.setText(day.getTimestamp().getDateString() + day.getTimestamp().getDateName(day.getTimestamp()));
        return row;
    }
    static class WeekHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtDate;
    }
}