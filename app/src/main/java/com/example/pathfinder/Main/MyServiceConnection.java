package com.example.pathfinder.Main;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public class MyServiceConnection implements ServiceConnection {
    private DisplayInformationActivity activity;

    public MyServiceConnection(DisplayInformationActivity activity){
        this.activity = activity;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        StepService.LocalBinder binder = (StepService.LocalBinder) service;
        activity.service = binder.getService();
        activity.bound = true;
        activity.service.setListenerActivity(activity);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        activity.bound = false;
    }
}
