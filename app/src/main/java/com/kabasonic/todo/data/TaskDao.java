package com.kabasonic.todo.data;

import android.telephony.SmsManager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert (Task task);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update (Task task);

    @Delete
    void delete (Task task);

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task WHERE id =:id")
    LiveData<Task> getTaskById(long id);
}
