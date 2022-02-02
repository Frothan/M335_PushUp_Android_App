package com.example.pushup;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class CounterActivity extends Activity implements SensorEventListener {

    TextView textViewTimer;
    TextView getTextViewPushupCounter;
    private int seconds = 0;
    private SensorManager sensorManager;
    private Sensor proximity;
    // Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;
    private int pushupCounter;
    boolean position = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_counter);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
         sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
         proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        running = true;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
       /* if (savedInstanceState != null) {

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
/*    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState)
    {
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

    // If the activity is resumed,
    // start the stopwatch
    // again if it was running previously.
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        if (wasRunning) {
            running = true;
        }
    }

    // Start the stopwatch running
    // when the Start button is clicked.
    // Below method gets called
    // when the Start button is clicked.
    public void onClickStart(View view)
    {
        running = true;
        runTimer();
    }

    public void onClickStop(View view)
    {
        running = false;
    }


    private void runTimer() {

        // Get the text view.
        final TextView timeView
                = (TextView) findViewById(
                R.id.textViewTimer);

        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
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