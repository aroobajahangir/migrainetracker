package com.example.maigrainetracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "migraine_records")
public class MigraineRecord implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public long recordDate;
    public int painLevel;
    public long duration;
    public String migraineType;
    public String notes;

    // Symptoms
    public boolean nausea;
    public boolean vomiting;
    public boolean lightSensitivity;
    public boolean soundSensitivity;
    public boolean dizziness;

    // Triggers
    public boolean stress;
    public boolean sleepDeprivation;
    public boolean screenTime;
    public boolean coffee;
    public boolean chocolate;
    public boolean skippedMeals;

    public String medication;
}
