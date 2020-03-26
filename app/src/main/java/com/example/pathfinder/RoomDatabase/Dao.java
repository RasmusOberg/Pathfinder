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

    //maybe remove
    @Insert
    void insert(Step step);

    @Query("UPDATE step_table SET stepCount = stepCount+1 WHERE date =:date AND user =:user")
    void addStep(String date, String user);

    @Query("SELECT * FROM user_table")
    List<User> getUsers();

    @Query("SELECT username FROM user_table")
    List<String> getAllUsernames();

    @Query("SELECT password FROM user_table WHERE username = :username")
    String getPassword(String username);

    @Query("SELECT * FROM step_table WHERE date =:date")
    Step getStep(String date);

    @Query("DELETE FROM step_table")
    void delete();

}
