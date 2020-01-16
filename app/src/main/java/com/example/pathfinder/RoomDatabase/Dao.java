package com.example.pathfinder.RoomDatabase;

import androidx.room.Insert;
import androidx.room.Query;

import com.example.pathfinder.Main.Step;
import com.example.pathfinder.Main.User;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(User user);

    @Insert
    void insert(Step step);

    @Query("SELECT * FROM user_table")
    List<User> getUsers();

    @Query("SELECT username FROM user_table")
    List<String> getAllUsernames();

    @Query("SELECT password FROM user_table WHERE username = :username")
    String getPassword(String username);


//    @Query("SELECT steps FROM user_table")
//    int getSteps();
}
