package com.example.pathfinder.Fragments;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compass, container, false);
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorListener = new SensorListener(this);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        compass = view.findViewById(R.id.compass);
        return view;
    }

}
