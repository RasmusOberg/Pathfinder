package com.example.pathfinder.Main;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.pathfinder.Fragments.CompassFragment;

public class SensorListener implements SensorEventListener {
    private CompassFragment fragment;
    private int shakeThreshold = 10;
    private long lastUpdateTime = 0;
    private float currentDegree = 0;

    public SensorListener(CompassFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x, y, z, lastX = 0, lastY = 0, lastZ = 0;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            float deltaX = Math.abs(lastX - x);
            float deltaY = Math.abs(lastY - y);
            float deltaZ = Math.abs(lastZ - z);

            if ((deltaX > shakeThreshold && deltaY > shakeThreshold)
                    || (deltaX > shakeThreshold && deltaZ > shakeThreshold)
                    || (deltaY > shakeThreshold && deltaZ > shakeThreshold)) {
                Toast.makeText(fragment.getActivity(), "TOAST", Toast.LENGTH_SHORT).show();
                fragment.shakeCompass();
            }
        }
        if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            rotateUsingOrientationSensor(event);
        }
    }

    public void rotateUsingOrientationSensor(SensorEvent event){
        if(System.currentTimeMillis() - lastUpdateTime > 250){
            float angleInDegree = event.values[0];
            RotateAnimation animation = new RotateAnimation(currentDegree, -angleInDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(250);
            animation.setFillAfter(true);
            fragment.animateCompass(animation);
            currentDegree = -angleInDegree;
            lastUpdateTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

}
