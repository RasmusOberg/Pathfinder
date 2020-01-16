package com.example.pathfinder.Main;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import androidx.fragment.app.Fragment;

import com.example.pathfinder.Fragments.CompassFragment;

public class SensorListener implements SensorEventListener {
    private CompassFragment fragment;

    public SensorListener(CompassFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
