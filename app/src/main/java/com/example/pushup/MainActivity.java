package com.example.pushup;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
/*        String message = eGoal.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        startActivity(intent);
    }

    public void saveData(View view) throws JSONException {

        EditText eName = (EditText) findViewById(R.id.Name);
        EditText eGoal = (EditText) findViewById(R.id.Goal);
        EditText eAge = (EditText) findViewById(R.id.Age);
        EditText eWeight = (EditText) findViewById(R.id.Weight);
        EditText eHeight = (EditText) findViewById(R.id.Height);

        //Muss noch geändert werden -> weil number of pushup ist der counter
        EditText eNumberOfPushups = (EditText) findViewById(R.id.Goal);
        User currentUser = new User();
        currentUser.setFullName(eName.getText().toString());
        currentUser.setGoal(eGoal.getText().toString());
        currentUser.setAge(eAge.getText().toString());
        currentUser.setWeight(eWeight.getText().toString());
        currentUser.setHeight(eHeight.getText().toString());
       currentUser.setNumberOfPushups(eNumberOfPushups.getText().toString());
        FileOutputStream outputStream;
        JSONObject user = new JSONObject();
        try {
            user.put("FullName", currentUser.getFullName());
            user.put("Goal", currentUser.getGoal());
            user.put("Age", currentUser.getAge());
            user.put("Weight", currentUser.getWeight());
            user.put("Height", currentUser.getHeight());
            user.put("PushUp", currentUser.getNumberOfPushups());
            user.put("Time", currentUser.getTime());
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(user);
            JSONObject userObj = new JSONObject();
            userObj.put("User", jsonArray);
            String jsonStr = userObj.toString();
            System.out.println("jsonString: "+jsonStr);
            outputStream = openFileOutput("UserData", Context.MODE_PRIVATE);
            outputStream.write(jsonStr.getBytes());
            outputStream.close();
            startPushUp(view);
        } catch (JSONException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}