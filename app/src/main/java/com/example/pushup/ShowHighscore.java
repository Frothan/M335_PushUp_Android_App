package com.example.pushup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShowHighscore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_highscore);
    }

    public void showHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}