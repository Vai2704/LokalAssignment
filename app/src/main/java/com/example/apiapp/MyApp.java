package com.example.apiapp;

import android.app.Application;

import androidx.room.Room;

import com.example.apiapp.database.AppDatabase;

public class MyApp extends Application {

    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "job_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
