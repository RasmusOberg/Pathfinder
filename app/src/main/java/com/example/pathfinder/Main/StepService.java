package com.example.pathfinder.Main;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pathfinder.RoomDatabase.Repository;

public class StepService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    private LocalBinder binder;
    private Repository repository;
    private DisplayInformationActivity listenerActivity;

    public void onCreate(){
        super.onCreate();
        binder = new LocalBinder();
        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null){
            stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            repository = new Repository(getApplication());
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        return null;
    }

    public void setListenerActivity(DisplayInformationActivity activity){
        this.listenerActivity = activity;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            listenerActivity.createStepsEntry();
            //repository.createStepsEntry();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(listenerActivity, "Service destroyed", Toast.LENGTH_SHORT).show();
    }

    public class LocalBinder extends Binder {
        StepService getService(){
            return StepService.this;
        }
    }
}
