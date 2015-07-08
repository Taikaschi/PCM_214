package de.fhs.pcm_214;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemArrayAdapter extends ArrayAdapter<Item> {

    private ArrayList<Item> model;
    private Context context;

    public ItemArrayAdapter(ArrayList<Item> model, Context context) {
        super(context, 0,model );
        this.model = model;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);

        TextView textView = (TextView) itemView.findViewById(R.id.textView);
        CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        final int i = position;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.get(i).checked = isChecked;
            }
        });
        textView.setText(model.get(position).recipeName);
        checkBox.setChecked(model.get(position).checked);

        return itemView;
    }

}
