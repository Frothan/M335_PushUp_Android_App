package com.example.pushup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText eName;
    EditText eGoal;
    EditText eAge;
    EditText eWeight;
    EditText eHeight;

    User currentUser = new User();

    ArrayList<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showHighscore(View view){
        Intent intent = new Intent(this, ShowHighscore.class);
        startActivity(intent);
    }

    public void startPushUp(View view){
        eGoal = (EditText) findViewById(R.id.Goal);
        eName = (EditText) findViewById(R.id.Name);
        eAge = (EditText) findViewById(R.id.Age);
        eWeight = (EditText) findViewById(R.id.Weight);
        eHeight = (EditText) findViewById(R.id.Height);

        Intent intent = new Intent(this, CounterActivity.class);
        String message = eGoal.getText().toString();
        intent.putExtra("goal", message);
        User currentUser = new User();
        currentUser.setId(2);
        currentUser.setFullName(eName.getText().toString());
        currentUser.setGoal(eGoal.getText().toString());
        currentUser.setAge(eAge.getText().toString());
        currentUser.setWeight(eWeight.getText().toString());
        currentUser.setHeight(eHeight.getText().toString());

        intent.putExtra("user", (Parcelable) currentUser);
        startActivity(intent);
    }
}