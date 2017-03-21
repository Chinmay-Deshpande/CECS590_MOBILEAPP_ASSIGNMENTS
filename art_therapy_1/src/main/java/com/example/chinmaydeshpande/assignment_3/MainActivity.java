package com.example.chinmaydeshpande.assignment_3;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mlight;
    Custom_view customcanvas;
    private static final int TIMEOUT = 300;
    private static final int Shaketimes=2;
    private long LastSensorShake,LastSensorTime;
    private long lastupdate;
    private int Shake_times = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mlight=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this,mlight,SensorManager.SENSOR_DELAY_NORMAL);

        lastupdate = System.currentTimeMillis();
        LastSensorTime = lastupdate;
        LastSensorShake = lastupdate;

        customcanvas= (Custom_view) findViewById(R.id.custom);
    }

    public void clearcanvas(View v)
    {
        customcanvas.clearCanvas();
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {

            long current_time = System.currentTimeMillis();

            if ((current_time - lastupdate) > TIMEOUT) {
                Shake_times = 0;
            }

            if((current_time-lastupdate)>100) {

                float acc = (sensorEvent.values[0] * sensorEvent.values[0] + sensorEvent.values[1] * sensorEvent.values[1] + sensorEvent.values[2] * sensorEvent.values[2]) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
                long actual_time = System.currentTimeMillis();
                if (acc > 2) {


                    if (++Shake_times > 1) {
                        lastupdate = actual_time;
                        clearcanvas(customcanvas);
                        Intent intent = new Intent(this, Eraser.class);
                        startService(intent);

                        Shake_times = 0;
                    }
                    lastupdate = current_time;
                }
            }
            else
            {

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
