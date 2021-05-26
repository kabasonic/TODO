package com.kabasonic.todo.di;

import android.app.Application;

import com.kabasonic.todo.AppTodo;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final AppTodo application;
    public ApplicationModule (AppTodo application){
        this.application = application;
    }

    @Provides
    AppTodo provideAppTodo(){
        return application;
    }


    @Provides
    Application provideApplication(){
        return application;
    }

}
