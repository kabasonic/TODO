package com.kabasonic.todo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.kabasonic.todo.R;

public class TaskDetailsFragment extends Fragment {

    public static final String TASK_ID = "TASK_ID";

    private long taskId;
    private Context context;

    private EditText titleField;
    private EditText descriptionField;
    private MaterialButton addTaskButton;
    private MaterialButton deleteTaskButton;

    public TaskDetailsFragment(){}

    public static TaskDetailsFragment newInstance(long taskId){
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(TASK_ID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        this.taskId = args.getLong(TASK_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_details, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleField = view.findViewById(R.id.inputTitle);
        descriptionField = view.findViewById(R.id.inputDesc);
        addTaskButton = view.findViewById(R.id.addTaskBt);
        deleteTaskButton = view.findViewById(R.id.delTaskBt);


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
