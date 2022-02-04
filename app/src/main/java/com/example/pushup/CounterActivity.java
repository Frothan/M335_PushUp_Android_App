package com.example.pushup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.Locale;

public class CounterActivity extends AppCompatActivity implements SensorEventListener {

    TextView textViewTimer;
    TextView getTextViewPushupCounter;
    TextView userGoal;
    Button startButton;
    Button stopButton;
    Button showResults;
    private int seconds = 0;
    private SensorManager sensorManager;
    private Sensor proximity;
    private boolean running;
    private boolean wasRunning;
    private int pushupCounter;
    boolean position = false;

    // MAIN
    EditText eName;
    EditText eGoal;
    EditText eAge;
    EditText eWeight;
    EditText eHeight;

    User currentUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
         sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
         proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        textViewTimer = (TextView) findViewById(R.id.textViewTimer);

        running = true;
        showResults = (Button) findViewById(R.id.showResults);
        showResults.setVisibility(View.GONE);



        String goal;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                goal= null;
            } else {
                goal= extras.getString("goal");
            }
        } else {
            goal= (String) savedInstanceState.getSerializable("goal");
        }

        userGoal = (TextView) findViewById(R.id.ShowGoal);
        userGoal.setText(goal);

        Bundle extras = getIntent().getExtras();
        currentUser = (User) extras.getParcelable("user");

      /*  if (savedInstanceState != null) {

            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }*/
        /*runTimer();*/
    }

    // Save the state of the stopwatch
    // if it's about to be destroyed.
   /* @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }*/

    // If the activity is paused,
    // stop the stopwatch.
    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        /*sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);*/
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStart(View view)
    {
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        running = true;
        runTimer();
    }

    public void onClickStop(View view) throws JSONException {
        sensorManager.unregisterListener(this);
        saveData(view);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.GONE);
        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setVisibility(View.GONE);
        showResults = (Button) findViewById(R.id.showResults);
        showResults.setVisibility(View.VISIBLE);

        running = false;

    }



    public void showResults(View view) throws JSONException
    {

        Intent intent = new Intent(this, ShowHighscore.class);
        startActivity(intent);
    }


    private void runTimer() {
        // Get the text view.
        final TextView timeView
                = (TextView) findViewById(
                R.id.textViewTimer);
        // Creates a new Handler
        final Handler handler
                = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%02d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timeView.setText(time);

                // If running is true, increment the
                // seconds variable.
                if (running) {
                    seconds++;
                }

                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }


    // Sensor

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];
        getTextViewPushupCounter = (TextView) findViewById(R.id.textViewPUshupCounter);
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (distance > 4 && !position) {
                position = true;
                getTextViewPushupCounter.setText("" + pushupCounter);
                pushupCounter++;
            } else if (distance < 4)
            {
                position = false;
            }
        }
    }


    //MAIN
    public void saveData(View view) throws JSONException {


        getTextViewPushupCounter = (TextView) findViewById(R.id.textViewPUshupCounter);
        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        //Muss noch geändert werden -> weil number of pushup ist der counter

        currentUser.setNumberOfPushups(getTextViewPushupCounter.getText().toString());
        currentUser.setTime(textViewTimer.getText().toString());
        JSONArray userArray = null;
        String responce = "";
        try {
            // Read json file
            Context context = this;
            File file = new File(context.getFilesDir(),"UserData.json");
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            if (line != null) {
                while (line != null) {
                    stringBuilder.append(line).append("\n");
                    line = bufferedReader.readLine();
                }
                responce = stringBuilder.toString();
                //

                JSONObject obj = new JSONObject(responce);
                // fetch JSONArray named users
                userArray = obj.getJSONArray("User");
            }
            else
                userArray = new JSONArray();
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

        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}