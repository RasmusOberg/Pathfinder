package com.example.pathfinder.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pathfinder.Main.Step;
import com.example.pathfinder.RoomDatabase.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repo;
    private List<Step> stepList;
    private LiveData<List<Step>> liveStepList;

    public MainViewModel(Application application){
        super(application);
        repo = new Repository(application);
        liveStepList = repo.getLiveStepList();
    }

    public LiveData<List<Step>> getLiveStepList(){
        return liveStepList;
    }

    public void delete(){
        repo.delete();
    }

    public void addStep(String date, String user){
        repo.addStep(date, user);
    }

    public void insertStep(Step step){
        updateOrInsertStep(step);
    }

    public void updateOrInsertStep(Step step1){
        Step step2 = repo.getStepByTime(step1.getDate());
        if(step2 == null){
            repo.insertStep(step1);
        }else{
            repo.addStep(step1.getDate(), step1.getUser());
        }
    }

    public String getCurrentUser(){
        return repo.getCurrentUser();
    }

}
