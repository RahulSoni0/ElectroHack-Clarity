package com.rahulsoni0.clarity.cache;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedListDao {

    @Query("SELECT * FROM SavedList ORDER BY uid DESC")
      List<SavedListEntityModel> getAlldata();

    @Insert
    void insertSavedData(SavedListEntityModel... savedListEntityModels);


    @Delete
    void deleteData(SavedListEntityModel... savedListEntityModels);

}
