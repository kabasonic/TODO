package com.kabasonic.todo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.kabasonic.todo.AppTodo;
import com.kabasonic.todo.R;
import com.kabasonic.todo.data.Task;
import com.kabasonic.todo.view.adapter.TaskListAdapter;
import com.kabasonic.todo.view.viewmodel.TaskDetailsViewModel;

import javax.inject.Inject;

public class TaskDetailsFragment extends Fragment {

    public static final String TASK_ID = "TASK_ID";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    TaskDetailsViewModel viewModel;

    private long taskId;
    private Context context;

    private EditText titleField;
    private EditText descriptionField;
    private EditText dateField;
    private MaterialButton saveTaskButton;
    private MaterialButton cancelTaskButton;
    private AutoCompleteTextView labelField;

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

        ((AppTodo) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

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
        dateField = view.findViewById(R.id.inputDate);
        saveTaskButton = view.findViewById(R.id.addTaskBt);
        cancelTaskButton = view.findViewById(R.id.delTaskBt);
        labelField = view.findViewById(R.id.inputLabel);

        viewModel = ViewModelProviders.of(this,viewModelFactory).get(TaskDetailsViewModel.class);

        initAutoCompleteLabel();
        saveTaskToDatabase();
        cancelTaskButton();

    }

    @Override
    public void onResume() {
        super.onResume();
        setDataField();
    }


    //finish activity;
    private void cancelTaskButton(){
        cancelTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    //save task and finish activity;
    private void saveTaskToDatabase(){
        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskId == -1){
                    viewModel.insert(getDataField());
                }else{
                    Task task = getDataField();
                    task.setId(taskId);
                    viewModel.update(task);
                }
                getActivity().finish();
            }
        });
    }

    // function get data with field when we created or modification task
    private Task getDataField(){
        String title = titleField.getText().toString();
        String description = descriptionField.getText().toString();
        String label = labelField.getText().toString();
        return new Task(title,description,label,0,true);
    }

    //function set data to field
    private void setDataField(){
        if(taskId!=-1){
            viewModel.getTaskById(taskId).observe(getViewLifecycleOwner(), new Observer<Task>() {
                @Override
                public void onChanged(Task task) {
                    titleField.setText(task.getTitle());
                    descriptionField.setText(task.getDescription());
                    labelField.setText(task.getLabel());
                    dateField.setText(String.valueOf(task.getTimestamp()));
                }
            });
        }
    }

    private void initAutoCompleteLabel(){
        String[] arrayLabels = {"Label1", "Label2"};
        ArrayAdapter<String> labelAdapter = new ArrayAdapter<String>(context, R.layout.auto_complete_item,R.id.label_item_id, arrayLabels);
        labelField.setAdapter(labelAdapter);
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
