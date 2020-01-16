package com.example.pathfinder.RoomDatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.pathfinder.Main.User;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Repository {
    private Dao dao;
    private List<String> allUserNames;
    private List<User> allUsers;

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
        String storedPassword = dao.getPassword(username);
        return (storedPassword.equals(password));
    }

    public boolean validateUsername(String username){
        return !allUserNames.contains(username);
    }

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
}
