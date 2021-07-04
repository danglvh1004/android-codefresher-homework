package com.example.lesson7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FolderDao {

    @Insert
    void insert(Folder folder);

    @Query("SELECT * FROM folder")
    LiveData<List<Folder>> getAllFolders();

    @Query("DELETE FROM folder")
    void deleteAll();

    @Query("SELECT * from folder LIMIT 1")
    Folder[] getAnyFolder();

    @Update
    public void updateFolder(Folder folder);
}
