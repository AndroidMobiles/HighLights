package com.tutecentral.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Jestin Jaye Tan on 1/31/2015.
 */
public class MyArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;
    SharedPreferences preferences;
    private final int layout;

    public MyArrayAdapter(Context context,int layout, String[] values) {
        super(context, layout, values);
        this.context=context;
        this.values=values;
        this.layout=layout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = null;
        if(convertView!=null){
            rowView=convertView;}
        else{
            rowView=inflater.inflate(layout, parent, false);

            TextView textView = (TextView) rowView.findViewById(R.id.user);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            final Button button=(Button) rowView.findViewById(R.id.yay);


            textView.setText(values[position]);

        }
        return rowView;
    }
    }

