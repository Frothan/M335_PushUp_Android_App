package com.example.pushup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPushUp(View view){
        Intent intent = new Intent(this, CounterActivity.class);
        startActivity(intent);
    }

    public void showHighscore(View view){
        Intent intent = new Intent(this, ShowHighscore.class);
        startActivity(intent);
    }

    public void saveData(View view) throws JSONException {
        EditText eName = (EditText) findViewById(R.id.Name);
        EditText eGoal = (EditText) findViewById(R.id.Goal);
        //Muss noch geähndert werden
        EditText eNumberOfPushups = (EditText) findViewById(R.id.Goal);


        User currentUser = new User();

        currentUser.setFullName(eName.getText().toString());
        currentUser.setGoal(Integer.parseInt(eGoal.getText().toString()));
        currentUser.setNumberOfPushups(Integer.parseInt(eNumberOfPushups.getText().toString()));

        FileOutputStream outputStream;

        JSONObject user = new JSONObject();
        try {
            user.put("Full Name", currentUser.getFullName());
            user.put("Goal", currentUser.getGoal());
            JSONArray jsonArray = new JSONArray();

            jsonArray.put(user);

            JSONObject userObj = new JSONObject();
            userObj.put("User", jsonArray);

            String jsonStr = userObj.toString();

            System.out.println("jsonString: "+jsonStr);

            outputStream = openFileOutput("UserData", Context.MODE_PRIVATE);
            outputStream.write(jsonStr.getBytes());
            outputStream.close();

        } catch (JSONException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}