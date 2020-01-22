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

    public Step(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
