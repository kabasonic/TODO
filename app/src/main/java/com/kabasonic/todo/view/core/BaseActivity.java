package com.kabasonic.todo.view.core;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {

    public void addFragmentToActivity(FragmentManager fragmentManager,
                                      Fragment fragment,
                                      int frameId,
                                      String tag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId,fragment,tag);
        transaction.commit();
    }
}
