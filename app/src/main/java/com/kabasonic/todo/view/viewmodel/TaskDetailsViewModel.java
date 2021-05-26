package com.kabasonic.todo.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kabasonic.todo.data.Task;
import com.kabasonic.todo.data.TaskRepository;

public class TaskDetailsViewModel extends ViewModel {

    private TaskRepository repository;

    public TaskDetailsViewModel(TaskRepository taskRepository){
        this.repository = taskRepository;
    }

    //get choose task by id with repository (database);
    public LiveData<Task> getTaskById(long id){
        return repository.getTaskById(id);
    }

    //insert task to database;
    public void insert(Task task){
        repository.insert(task);
    }

    //update task in database;
    public void update(Task task){
        repository.update(task);
    }

    //delete task with database;
    public void delete(Task task){
        repository.delete(task);
    }


}
