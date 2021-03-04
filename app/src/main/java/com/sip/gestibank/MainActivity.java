package com.sip.gestibank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void interfaceConversionDevise(View v){

    }


    public void addNewClient(View v){

        Intent intent = new Intent(MainActivity.this, NewClient.class);
        startActivity(intent);
    }


    public void interfaceAuthentification(View v){
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }

}