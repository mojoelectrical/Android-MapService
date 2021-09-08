package com.example.michaelasafoadjei_comp304_001_finalterm;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);
    //
    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    List<Task> findTask(int taskId);
    //
    @Query("DELETE FROM Task WHERE taskId = :taskId")
    void deleteTask(int taskId);
    //
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();
}
