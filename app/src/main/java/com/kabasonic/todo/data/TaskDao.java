package com.kabasonic.todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    //insert task;
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert (Task task);

    //update task;
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update (Task task);

    //delete task;
    @Query("DELETE FROM task WHERE id =:id")
    void delete (long id);

    //show all tasks
    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

    //show selected task using id task
    @Query("SELECT * FROM task WHERE id =:id")
    LiveData<Task> getTaskById(long id);

    //update task active status using id;
    @Query("UPDATE task SET active =:activeValue WHERE id =:idItem")
    void updateStatusTask(long idItem, int activeValue);

}
