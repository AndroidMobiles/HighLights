package com.tutecentral.navigationdrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jestin Jaye Tan on 2/8/2015.
 */
public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login);
        super.onCreate(savedInstanceState);
        getActionBar().hide();
    }
    public void onClick(View v){
        Intent i;
        if(v.getId()==R.id.login) {
            i = new Intent(this, MainActivity.class);
        }
        else{
            i = new Intent(this, Register.class);
        }
        startActivity(i);

    }

}
