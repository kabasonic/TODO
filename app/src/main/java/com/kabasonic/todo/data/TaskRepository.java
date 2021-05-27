package com.kabasonic.todo.data;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class TaskRepository {

    private final TaskDao taskDao;

    @Inject
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
    public void delete(long id){
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                taskDao.delete(id);
                return true;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    //update task status active;
    public void updateStatusActive(long id, boolean active){
        Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if(active)
                    taskDao.updateStatusTask(id,1);
                else
                    taskDao.updateStatusTask(id,0);
                return true;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

}
