package com.tutecentral.navigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jestin Jaye Tan on 2/12/2015.
 */
public class ListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] nam;
    String[] com={"Hi! I like this phone","I believe it will suit your needs", "Ill try it then!", "Okay", "It's on sale. Right here and now.", "Really?", "Yes.", "Yay!"};
    public ListViewAdapter(Context context,String[] nam){
        super(context, R.layout.comment,nam);
        this.context= context;
        this.nam=nam;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if(convertView!=null){
            view=convertView;}
        else {
            view = inflater.inflate(R.layout.comment, parent, false);
            ImageView profp = (ImageView) view.findViewById(R.id.imageF);
            TextView textview = (TextView) view.findViewById(R.id.nameF);
            TextView textview2 = (TextView) view.findViewById(R.id.commentF);
            textview.setText(nam[position]);
            textview2.setText(com[position]);
            String s = nam[position];
            if (s.startsWith("Russel")) {
                profp.setImageResource(R.drawable.user1);
            } else {
                profp.setImageResource(R.drawable.user2);
            }
        }
        return view;
    }
}
