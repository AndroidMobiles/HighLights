package com.tutecentral.navigationdrawer;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Jestin Jaye Tan on 2/12/2015.
 */
public class Comment extends ListActivity {
    Context context=this;
    String[] nam= new String[] {"Russel","Notrussel","Russel","Notrussel","Russel","Notrussel","Russel", "Notrussel"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListViewAdapter adapt = new ListViewAdapter(context,nam);
        View footer= getLayoutInflater().inflate(R.layout.footer,null);
        ListView list=getListView();
        list.addFooterView(footer);
        setListAdapter(adapt);
    }
    public void onClick(View v)
    {
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
