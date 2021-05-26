package com.kabasonic.todo.data;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    //get all tasks with database
    public LiveData<List<Task>> getAllTasks(){
        return taskDao.getAllTasks();
    }

    //get which task with database using id task;
    public LiveData<Task> getTaskById(long id){
        return taskDao.getTaskById(id);
    }

    //insert task to database;
    public void insert(Task task){
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                taskDao.insert(task);
                return true;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    //update task to database;
    public void update(Task task){
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                taskDao.update(task);
                return true;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    //delete task with database;
    public void delete(Task task){
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                taskDao.delete(task);
                return true;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

}
