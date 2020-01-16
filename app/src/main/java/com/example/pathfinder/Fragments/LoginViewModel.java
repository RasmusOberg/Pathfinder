package com.example.pathfinder.Fragments;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.pathfinder.Main.User;
import com.example.pathfinder.RoomDatabase.Dao;
import com.example.pathfinder.RoomDatabase.Repository;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginViewModel extends AndroidViewModel {
    private Repository repository;
    private List<String> allUsers;

    public LoginViewModel(Application application){
        super(application);
        repository = new Repository(application);
        allUsers = repository.getAllUserNames();
    }

    public boolean validateLogin(String username, String password){
        Log.d(TAG, "validateLogin: " + username + " " + password);
        return repository.validateLogin(username, password);
    }

    public boolean validateUsername(String username){
        return repository.validateUsername(username);
    }

    public void insertNewUser(User user){
        repository.insertNewUser(user);
    }
}
