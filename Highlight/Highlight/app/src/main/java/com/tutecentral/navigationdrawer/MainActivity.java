package com.tutecentral.navigationdrawer;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import static com.tutecentral.navigationdrawer.R.*;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    Button button;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        // Initializing
        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(id.drawer_layout);
        mDrawerList = (ListView) findViewById(id.left_drawer);

        mDrawerLayout.setDrawerShadow(drawable.drawer_shadow,
                GravityCompat.START);

        // Add Drawer Item to dataList
        dataList.add(new DrawerItem(true)); // adding a spinner to the list

        dataList.add(new DrawerItem("Welcome!")); // adding a header to the list
        dataList.add(new DrawerItem("Feeds", drawable.ic_action_feeds));
        dataList.add(new DrawerItem("Boards", drawable.ic_action_labels));
        dataList.add(new DrawerItem("Browse", drawable.ic_action_browse));
        dataList.add(new DrawerItem("Profile", drawable.ic_action_profile));
        dataList.add(new DrawerItem("Logout", drawable.ic_action_logout));


        adapter = new CustomDrawerAdapter(this, layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawable.ic_menu, string.drawer_open,
                string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {

            if (dataList.get(0).isSpinner()
                    & dataList.get(1).getTitle() != null) {
                SelectItem(2);
            } else if (dataList.get(0).getTitle() != null) {
                SelectItem(1);
            } else {
                SelectItem(0);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    public void onClick(View v) {

        SharedPreferences pref;
            pref =getSharedPreferences("pref", Context.MODE_PRIVATE);
            boolean activated = pref.getBoolean("activated", false);
            switch (v.getId()) {
                case id.yay:
                    if (activated == false) {
                        v.getBackground().setColorFilter(Color.parseColor("#1a4053"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(this, "Liked", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("activated", true);
                        editor.commit();
                    } else {
                        v.getBackground().setColorFilter(Color.parseColor("#648290"), PorterDuff.Mode.SRC_ATOP);
                        Toast.makeText(this, "Unliked", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean("activated", false);
                        editor.remove("activated");
                        editor.commit();
                    }
                    break;
            case id.follow:
                if (activated == false) {  // User hasn't actived the promocode -> activate it
                    v.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("activated", true);
                    editor.commit();
                } else {
                    v.getBackground().setColorFilter(Color.parseColor("#648290"), PorterDuff.Mode.SRC_ATOP);
                    Toast.makeText(this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("activated", false);
                    editor.commit();
                }
                break;
            case id.video:
            case id.image:
                v.getBackground().setColorFilter(Color.parseColor("#1a4053"), PorterDuff.Mode.SRC_ATOP);
                Toast.makeText(this, "Upload", Toast.LENGTH_SHORT).show();
                break;
            case id.posted:
                v.setBackgroundColor(0xFF1a4053);
                Toast.makeText(this, "Post", Toast.LENGTH_SHORT).show();
                break;

            case id.comment:
                Intent i= new Intent(this, Comment.class);
                startActivity(i);
                break;
            }
    }


    public void SelectItem(int position) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (position) {

            case 2:
                fragment = new FragmentFeeds();

                break;
            case 3:
                fragment = new FragmentBoard();

                break;
            case 4:
                fragment = new FragmentBrowse();

                break;
            case 5:
                fragment = new FragmentProfile();

                break;
            case 6:
                Intent intent=new Intent(this, Login.class);
                startActivity(intent);

                break;




            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if (dataList.get(position).getTitle() == null) {
                SelectItem(position);
            }

        }
    }

}

