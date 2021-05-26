package com.kabasonic.todo.di;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.kabasonic.todo.data.TaskDao;
import com.kabasonic.todo.data.TaskDatabase;
import com.kabasonic.todo.data.TaskRepository;
import com.kabasonic.todo.view.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final TaskDatabase database;

    public RoomModule(Application application){
        this.database = Room.databaseBuilder(
                application,
                TaskDatabase.class,
                "task.db"
        ).build();
    }

    @Provides
    @Singleton
    TaskRepository provideTaskRepository(TaskDao taskDao){
        return new TaskRepository(taskDao);
    }

    @Provides
    @Singleton
    TaskDao provideTaskDao(TaskDatabase database){
        return database.taskDao();
    }

    @Provides
    @Singleton
    TaskDatabase provideTaskDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(TaskRepository repository){
        return new CustomViewModelFactory(repository);
    }

}
