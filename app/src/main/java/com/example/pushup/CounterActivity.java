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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        if (wasRunning) {
            running = true;
        }
    }

    public void onClickStart(View view)
    {
        running = true;
        runTimer();
    }

    public void onClickStop(View view)
    {
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setVisibility(View.GONE);
        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setVisibility(View.GONE);
        showResults = (Button) findViewById(R.id.showResults);
        showResults.setVisibility(View.VISIBLE);

        running = false;
    }

    public void showResults(View view)
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
            if (distance > 8 && !position) {
                position = true;
                getTextViewPushupCounter.setText("" + pushupCounter);
                pushupCounter++;
            } else if (distance < 8)
            {
                position = false;
            }
        }
    }
}