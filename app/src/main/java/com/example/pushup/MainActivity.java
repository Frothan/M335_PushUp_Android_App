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
    EditText eNumberOfPushups;
    Button continueBtn;

    User currentUser = new User();

    ArrayList<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



  /*  public void saveData(View view) throws JSONException {

        eName = (EditText) findViewById(R.id.Name);
        eGoal = (EditText) findViewById(R.id.Goal);
        eAge = (EditText) findViewById(R.id.Age);
        eWeight = (EditText) findViewById(R.id.Weight);
        eHeight = (EditText) findViewById(R.id.Height);
        eNumberOfPushups = (EditText) findViewById(R.id.Goal);
        //Muss noch geändert werden -> weil number of pushup ist der counter

        currentUser.setId(6);
        currentUser.setFullName(eName.getText().toString());
        currentUser.setGoal(eGoal.getText().toString());
        currentUser.setAge(eAge.getText().toString());
        currentUser.setWeight(eWeight.getText().toString());
        currentUser.setHeight(eHeight.getText().toString());
        currentUser.setNumberOfPushups(eNumberOfPushups.getText().toString());

        try {
            // Read json file
            Context context = this;
            File file = new File(context.getFilesDir(),"UserData.json");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            String responce = stringBuilder.toString();
            //

            JSONObject obj = new JSONObject(responce);
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("User");

            bufferedReader.close();



            userArray.put(currentUser.toJSON());
            JSONObject userObj = new JSONObject();
            userObj.put("User", userArray);
            responce = userObj.toString();

            // Write json file
            // Define the File Path and its Name
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(responce);
            bufferedWriter.close();


            *//*startPushUp(view);*//*
        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/

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