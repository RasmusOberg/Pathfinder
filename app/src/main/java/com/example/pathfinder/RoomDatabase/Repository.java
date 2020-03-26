package com.example.pathfinder.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.pathfinder.Main.Step;
import com.example.pathfinder.Main.User;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Repository {
    private Dao dao;
    private List<String> allUserNames;
    private List<User> allUsers;
    private LiveData<List<Step>> liveStepList;
    private String currentUser;

    public Repository(Application application){
        Database db = Database.getInstance(application);
        dao = db.dao();

        try{
            allUserNames = dao.getAllUsernames();
            allUsers = dao.getUsers();
            for(int i = 0; i < allUsers.size(); i++){
                Log.d(TAG, "Repository: " + allUsers.get(i).getName() + " : PW= " + allUsers.get(i).getPassword());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public List<String> getAllUserNames() {
        return allUserNames;
    }

    public boolean validateLogin(String username, String password){
        currentUser = username;
        String storedPassword = dao.getPassword(username);
        return (storedPassword.equals(password));
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean validateUsername(String username){
        return !allUserNames.contains(username);
    }



    public LiveData<List<Step>> getLiveStepList() {
        return liveStepList;
    }

    public void delete() {
        dao.delete();
    }


    public Step getStepByTime(String date) {
        return dao.getStep(date);
    }


    //adds new user
    public void insertNewUser(User user){
        new InsertUserTask(dao).doInBackground(user);
    }

    private static class InsertUserTask extends AsyncTask<User, Void, Void>{
        private Dao dao;

        public InsertUserTask(Dao dao){
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(User... users) {
            dao.insert(users[0]);
            return null;
        }
    }

    //inserts
    public void insertStep(Step step1) {
        new InsertStepAsync(dao).execute(step1);
    }

    private static class InsertStepAsync extends AsyncTask<Step, Void, Void>{
        private Dao dao;

        public InsertStepAsync(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Step... steps) {
            dao.insert(steps[0]);
            return null;
        }
    }


    //adds or updates
    public void addStep(String date, String user) {
        new AddStepAsync(dao, date, user).execute();
    }

    private class AddStepAsync extends AsyncTask<String, String, Void>{
        private Dao dao;
        private String date;
        private String user;

        public AddStepAsync(Dao dao, String date, String user) {
            this.dao = dao;
            this.date = date;
            this.user = user;
        }

        @Override
        protected Void doInBackground(String... strings) {
            dao.addStep(date, user);
            return null;
        }
    }
}
