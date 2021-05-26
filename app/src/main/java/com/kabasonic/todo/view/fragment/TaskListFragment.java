package com.kabasonic.todo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabasonic.todo.R;

public class TaskListFragment extends Fragment {

    private static final String TASK_ID = "EXTRA_ITEM_ID";
    private static final String DETAIL_FRAG = "DETAIL_FRAG";

    private Context context;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ProgressBar progressBar;
    private ImageView imageView;

    public TaskListFragment(){}

    public static TaskListFragment newInstance(){
        return new TaskListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_items, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.listRecyclerViewId);
        floatingActionButton = view.findViewById(R.id.createTaskId);
        progressBar = view.findViewById(R.id.pbListItemsId);
        imageView = view.findViewById(R.id.infoImageId);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
