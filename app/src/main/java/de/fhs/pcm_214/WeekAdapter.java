package de.fhs.pcm_214;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sebastian on 08.07.2015.
 */
public class WeekAdapter extends ArrayAdapter<Week> {

    Context context;
    int layoutResourceId;
    Week data[] = null;

    public WeekAdapter(Context context, int layoutResourceId, Week[] data) {
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

            row.setTag(holder);
        }
        else
        {
            holder = (WeekHolder)row.getTag();
        }

        Week week = data[position];
        holder.txtTitle.setText(week.title);
        holder.imgIcon.setImageResource(week.icon);

        return row;
    }

    static class WeekHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}