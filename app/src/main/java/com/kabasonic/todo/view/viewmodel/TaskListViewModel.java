package com.kabasonic.todo.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabasonic.todo.data.Task;
import com.kabasonic.todo.data.TaskRepository;

import java.util.List;

public class TaskListViewModel extends ViewModel {

    private TaskRepository repository;

    public TaskListViewModel(TaskRepository taskRepository){
        this.repository = taskRepository;
    }

    //get all tasks with repository;
    public LiveData<List<Task>> getAllTasks(){
        return repository.getAllTasks();
    }

    //delete choose task with database;
    public void delete(long id){
        repository.delete(id);
    }

    public void updateTaskStatus(long id, boolean active){
        repository.updateStatusActive(id,active);
    }

}
