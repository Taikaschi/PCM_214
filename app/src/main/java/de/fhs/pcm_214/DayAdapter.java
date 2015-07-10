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
 * Created by soma on 09.07.2015.
 */
public class DayAdapter extends ArrayAdapter<Day> {

    Context context;
    int layoutResourceId;
    Day data[] = null;

    static class DayHolder {
        ImageView icon;
        TextView text;

    }

    public DayAdapter(Context context, int layoutResourceId, Day[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DayHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new DayHolder();
            holder.icon = (ImageView) row.findViewById(R.id.image);
            //holder.text = (TextView)row.findViewById(R.id.textDay);


            row.setTag(holder);
        } else {
            holder = (DayHolder) row.getTag();
        }

        Day day = data[position];
        //holder.text.setText(day.);
        //holder.icon.setImageResource();

        return row;
    }
}

