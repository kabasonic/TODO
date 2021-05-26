package com.kabasonic.todo.di;

import android.app.Application;

import com.kabasonic.todo.view.fragment.TaskDetailsFragment;
import com.kabasonic.todo.view.fragment.TaskListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(TaskListFragment taskListFragment);
    void inject(TaskDetailsFragment taskDetailsFragment);

    Application application();

}
