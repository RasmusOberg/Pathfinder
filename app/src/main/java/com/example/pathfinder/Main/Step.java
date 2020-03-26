package com.example.pathfinder.Main;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step_table")
public class Step {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "stepCount")
    private int stepCount;

    @ColumnInfo(name = "user")
    private String user;

    public Step(int stepCount, String date, String user){
        this.stepCount = stepCount;
        this.date = date;
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getStepCount() {
        return stepCount;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
