package de.fhs.pcm_214;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DayAdapter extends ArrayAdapter<Day> {

    Context context;
    int layoutResourceId;
    ArrayList<Day> data = null;
    TextView textDateName = null;
    TextView textDate = null;
    ImageView imageView = null;

    public DayAdapter(Context context, int layoutResourceId, ArrayList<Day> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_view_item_row, parent, false);

        textDate = (TextView) itemView.findViewById(R.id.textDate);
        textDateName = (TextView)itemView.findViewById(R.id.textDateName);

        Day day = data.get(position);
        textDateName.setText(day.getTimestamp().getDateName(day.getTimestamp()));
        textDate.setText(day.getTimestamp().getDateName(day.getTimestamp()) + "," + day.getTimestamp().getDateString());

        return itemView;
    }
}

