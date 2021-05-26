package com.kabasonic.todo.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.kabasonic.todo.R;
import com.kabasonic.todo.view.core.BaseActivity;
import com.kabasonic.todo.view.fragment.TaskDetailsFragment;

public class TaskDetailsActivity extends BaseActivity {
    public static final String TASK_ID = "TASK_ID";
    public static final String TASK_DETAIL_FRAGMENT = "TASK_DETAIL_FRAGMENT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        long taskId = intent.getLongExtra(TASK_ID,-1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        TaskDetailsFragment taskDetailsFragment = (TaskDetailsFragment) fragmentManager.findFragmentByTag(TASK_DETAIL_FRAGMENT);
        if(taskDetailsFragment== null){
            taskDetailsFragment = TaskDetailsFragment.newInstance(taskId);
        }
        addFragmentToActivity(fragmentManager,taskDetailsFragment, R.id.fragmentContainer,TASK_DETAIL_FRAGMENT);

    }
}
