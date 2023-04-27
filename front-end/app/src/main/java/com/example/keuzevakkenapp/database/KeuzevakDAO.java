package com.example.keuzevakkenapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface KeuzevakDAO {

    // Get all data query
    @Query("SELECT * FROM Keuzevak")
    List<Keuzevak> getAll();

    // Insert query
    @Insert(onConflict = REPLACE)
    void insertKeuzevak(Keuzevak keuzevak);

    // Delete query
    @Delete
    void delete(Keuzevak keuzevak);
}
