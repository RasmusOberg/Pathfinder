package com.example.pathfinder.Main;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step_table")
public class Step {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Step(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
