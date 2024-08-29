package com.example.apiapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {JobEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JobDAO jobDAO();
}
