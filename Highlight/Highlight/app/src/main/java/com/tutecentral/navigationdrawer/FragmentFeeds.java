package com.tutecentral.navigationdrawer;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentFeeds extends ListFragment {
    String[] values= {"Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "MACOSX", "Linux", "OS2"};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MyArrayAdapter adapter= new MyArrayAdapter(getActivity(), R.layout.fragment_feeds, values);
        View header=getActivity().getLayoutInflater().inflate(R.layout.header, null);
        ListView listView=getListView();
        listView.addHeaderView(header);
        setListAdapter(adapter);
    }

}
