package com.example.pathfinder.Fragments;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pathfinder.Main.SensorListener;
import com.example.pathfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompassFragment extends Fragment {
    private ImageView compass;
    private SensorListener sensorListener;
    private SensorManager sensorManager;
    private Sensor accelerometer, magnetometer, orientation;
    private boolean accPresent, magnePresent, orienPresent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorListener = new SensorListener(this);
        registerListeners();
        compass = view.findViewById(R.id.compass);
        return view;
    }

    public void shakeCompass(){
        Toast.makeText(getContext(), "Shake!", Toast.LENGTH_SHORT).show();
    }

    public void animateCompass(Animation animation){
        compass.startAnimation(animation);
    }

    public void registerListeners(){
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            accPresent = true;
        }else{
            Toast.makeText(getContext(), "Accelerometer not found!",Toast.LENGTH_SHORT).show();
            accPresent = false;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            sensorManager.registerListener(sensorListener, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            magnePresent = true;
        }else{
            Toast.makeText(getContext(), "Magnetometer not found!",Toast.LENGTH_SHORT).show();
            magnePresent = false;
        }
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null){
            orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            sensorManager.registerListener(sensorListener, orientation, SensorManager.SENSOR_DELAY_NORMAL);
            orienPresent = true;
        }else{
            Toast.makeText(getContext(), "Orientation not found!",Toast.LENGTH_SHORT).show();
            orienPresent = false;
        }

    }

}
