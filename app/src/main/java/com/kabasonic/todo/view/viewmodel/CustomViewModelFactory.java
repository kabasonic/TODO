package com.kabasonic.todo.view.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kabasonic.todo.data.TaskRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final TaskRepository repository;

    @Inject
    public CustomViewModelFactory(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskListViewModel.class))
            return (T) new TaskListViewModel(repository);

        else if (modelClass.isAssignableFrom(TaskDetailsViewModel.class))
            return (T) new TaskDetailsViewModel(repository);
        else {
            throw new IllegalArgumentException("ViewModel is not found");
        }
    }

}
