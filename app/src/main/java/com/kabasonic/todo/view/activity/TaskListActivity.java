package com.kabasonic.todo.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.kabasonic.todo.R;
import com.kabasonic.todo.view.core.BaseActivity;
import com.kabasonic.todo.view.fragment.TaskListFragment;

public class TaskListActivity extends BaseActivity {

    private static final String LIST_FRAG = "LIST_FRAG";

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        TaskListFragment fragment = (TaskListFragment) manager.findFragmentByTag(LIST_FRAG);

        if (fragment == null) {
            fragment = TaskListFragment.newInstance();
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.fragmentContainer,
                LIST_FRAG
        );

    }
}
