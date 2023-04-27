package com.example.keuzevakkenapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Add database entities
@Database(entities = {Keuzevak.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KeuzevakDAO villagerDAO();

    // Create database instance
    private static AppDatabase instance;

    // Define database name
    private static String DATABASE_NAME = "Keuzevak";

    static synchronized AppDatabase getInstance(Context context) {
        // Check condition
        if(instance == null) {
            // When database is null
            // Initialize database
            instance = create(context);
        }
        // Return database
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}