package com.tutecentral.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.register);
        super.onCreate(savedInstanceState);
        getActionBar().hide();
    }
    public void onClick(View v){
        Intent i= new Intent(this, Login.class);
        startActivity(i);

    }

}
