package com.example.pathfinder.Main;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.pathfinder.RoomDatabase.Repository;

public class StepService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor stepDetectorSensor;
    private LocalBinder binder;
    private Repository repository;
    private ChangeListener listener;

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

    public void setListenerActivity(ChangeListener listener){
        this.listener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        repository.createStepsEntry();
        listener.update();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class LocalBinder extends Binder {
        StepService getService(){
            return StepService.this;
        }
    }
}
