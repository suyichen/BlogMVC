package com.syc.blogmvc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.syc.normal.NormalLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Normal(View view) {

        startActivity(new Intent(MainActivity.this, NormalLogin.class));
    }

    public void MvcMode(View view) {

    }
}
