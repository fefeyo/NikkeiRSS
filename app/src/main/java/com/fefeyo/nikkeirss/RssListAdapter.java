package com.fefeyo.nikkeirss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sam on 2015/02/21.
 */
public class RssListAdapter extends ArrayAdapter<Item> {
    private LayoutInflater inflater;
    private TextView title,date;


    public RssListAdapter(Context context, List<Item> objects) {
        super(context,0, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View v = convertView;

        if(convertView == null){
            v = inflater.inflate(R.layout.item_row,null);
        }
        Item item = this.getItem(position);
        if(item != null){
            String c_title = item.getTitle();
            title = (TextView)v.findViewById(R.id.title);
            title.setText(c_title);
            String c_date = item.getDate();
            date = (TextView)v.findViewById(R.id.date);
            date.setText(c_date);
        }
        return v;
    }
}
