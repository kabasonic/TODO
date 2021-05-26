package com.kabasonic.todo.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kabasonic.todo.AppTodo;
import com.kabasonic.todo.R;
import com.kabasonic.todo.data.Task;
import com.kabasonic.todo.view.activity.TaskDetailsActivity;
import com.kabasonic.todo.view.adapter.TaskListAdapter;
import com.kabasonic.todo.view.viewmodel.TaskListViewModel;

import java.util.List;

import javax.inject.Inject;

public class TaskListFragment extends Fragment {

    private static final String TASK_ID = "TASK_ID";
    private static final String DETAIL_FRAG = "DETAIL_FRAG";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    TaskListViewModel viewModel;

    private Context context;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ImageView imageView;

    private TaskListAdapter taskListAdapter;

    public TaskListFragment(){}

    public static TaskListFragment newInstance(){
        return new TaskListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppTodo) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
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
        imageView = view.findViewById(R.id.infoImageId);

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TaskListViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        createNewTask();
        buildAdapter();
        getAllTasks();
    }

    private void buildAdapter(){
        taskListAdapter = new TaskListAdapter(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(taskListAdapter);
        taskListAdapter.setOnItemClickListener(new TaskListAdapter.OnItemClickListener() {

            @Override
            public void onClickItemTask(long id) {
                Activity container = getActivity();
                Intent intent = new Intent(container, TaskDetailsActivity.class);
                intent.putExtra(TASK_ID,id);
                startActivity(intent);
            }
        });
    }

    //show all task in database;
    private void getAllTasks(){
        viewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(tasks.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(context,"Task list is epmpty",Toast.LENGTH_LONG).show();
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                    taskListAdapter.setTaskList(tasks);
                    taskListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    //Create new task;
    private void createNewTask(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity container = getActivity();
                Intent intent = new Intent(container, TaskDetailsActivity.class);
                startActivity(intent);
            }
        });
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
