package com.kabasonic.todo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.kabasonic.todo.AppTodo;
import com.kabasonic.todo.R;
import com.kabasonic.todo.data.Task;
import com.kabasonic.todo.view.viewmodel.TaskDetailsViewModel;

import javax.inject.Inject;

public class TaskDetailsFragment extends Fragment {

    public static final String TASK_ID = "TASK_ID";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    TaskDetailsViewModel viewModel;

    private long taskId;
    private Context context;

    private View view;
    private EditText titleField;
    private EditText dateField;
    private MaterialButton saveTaskButton;
    private MaterialButton cancelTaskButton;
    private AutoCompleteTextView labelField;
    private MaterialToolbar toolbar;
    private ActionBar actionBar;

    public TaskDetailsFragment() {
    }

    public static TaskDetailsFragment newInstance(long taskId) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(TASK_ID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //saved instance state
        outState.putString(getString(R.string.FIELD_TITLE), titleField.getText().toString());
        outState.putString(getString(R.string.FIELD_DATE), dateField.getText().toString());
        outState.putString(getString(R.string.FIELD_LABEL), labelField.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppTodo) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
        //get task id
        Bundle args = getArguments();
        this.taskId = args.getLong(TASK_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_details, container, false);

        titleField = view.findViewById(R.id.inputTitle);
        dateField = view.findViewById(R.id.inputDate);
        saveTaskButton = view.findViewById(R.id.addTaskBt);
        cancelTaskButton = view.findViewById(R.id.delTaskBt);
        labelField = view.findViewById(R.id.inputLabel);
        toolbar = view.findViewById(R.id.toolbarDetailsFragment);

        //saved instance state
        if (savedInstanceState != null) {
            String title = savedInstanceState.getString(getString(R.string.FIELD_TITLE));
            String date = savedInstanceState.getString(getString(R.string.FIELD_DATE));
            String label = savedInstanceState.getString(getString(R.string.FIELD_LABEL));
            titleField.setText(title);
            dateField.setText(date);
            labelField.setText(label);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //build toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setHasOptionsMenu(true);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskDetailsViewModel.class);

        initAutoCompleteLabel();

    }

    @Override
    public void onResume() {
        super.onResume();

        //listener save task button
        saveTaskToDatabase();

        //listener cancel task button
        cancelTaskButton();

        //set data task to fields
        setDataField();

        //select date with calendar
        selectDate();
    }

    //validation fields
    private boolean validationField(View view) {
        if (titleField.getText().toString().isEmpty()
                || dateField.getText().toString().isEmpty()
                || labelField.getText().toString().isEmpty()) {
            Snackbar.make(context, view, "Please fill all fields", Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    //select date with calendar
    private void selectDate() {
        MaterialDatePicker.Builder<Pair<Long, Long>> materialDateBuilder = MaterialDatePicker.Builder.dateRangePicker();
        materialDateBuilder.setTitleText(R.string.select_a_date);
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
        materialDateBuilder.build();
        dateField.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            materialDatePicker.show(fragmentManager, getString(R.string.MATERIAL_DATE_PICKER));
        });
        materialDatePicker.addOnPositiveButtonClickListener(
                selection -> {
                    String dateString = materialDatePicker.getHeaderText();
                    dateField.setText(dateString, TextView.BufferType.EDITABLE);
                });
    }

    //finish activity;
    private void cancelTaskButton() {
        cancelTaskButton.setOnClickListener(v -> {
            Snackbar.make(context,view, getResources().getString(R.string.task_not_saved),Snackbar.LENGTH_LONG).show();
            getActivity().finish();
        });
    }

    //save task and finish activity;
    private void saveTaskToDatabase() {
        saveTaskButton.setOnClickListener(v -> {
            boolean result = validationField(view);
            if (result) {
                if (taskId == -1) {
                    viewModel.insert(getDataField());
                } else {
                    Task task = getDataField();
                    task.setId(taskId);
                    viewModel.update(task);
                }
                Snackbar.make(context,view, getResources().getString(R.string.task_save),Snackbar.LENGTH_LONG).show();
                getActivity().finish();
            }
        });
    }

    // function get data with field when we created or modification task
    private Task getDataField() {
        String title = titleField.getText().toString();
        String label = labelField.getText().toString();
        String date = dateField.getText().toString();
        return new Task(title, label, date, false);
    }

    //function set data to field;
    private void setDataField() {
        if (taskId != -1) {
            viewModel.getTaskById(taskId).observe(getViewLifecycleOwner(), task -> {
                if (task != null) {
                    if(titleField.getText().toString().isEmpty()){
                        titleField.setText(task.getTitle());
                    }
                    if(labelField.getText().toString().isEmpty()){
                        labelField.setText(task.getLabel());
                    }
                    if(dateField.getText().toString().isEmpty()){
                        dateField.setText(String.valueOf(task.getDate()));
                    }
                }
            });
        }
    }

    //build adapter for auto complete label field
    private void initAutoCompleteLabel() {
        String[] arrayLabels = getResources().getStringArray(R.array.label);
        ArrayAdapter<String> labelAdapter = new ArrayAdapter<String>(context, R.layout.auto_complete_item, R.id.label_item_id, arrayLabels);
        labelField.setAdapter(labelAdapter);
    }

    //create dialog window;
    private void dialogDeleteTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.delete_a_task)
                .setMessage(R.string.text_delete_dialog)
                .setPositiveButton(R.string.okay, (dialog, id) -> {
                    viewModel.delete(taskId);
                    getActivity().finish();
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    dialog.dismiss();
                });
        builder.create();
        builder.show();
    }

    //attach fragment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //detach fragment
    @Override
    public void onDetach() {
        super.onDetach();
    }

    //create menu (inflate)
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_task_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //listener menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                dialogDeleteTask();
                break;
            case android.R.id.home:
                getActivity().finish();
                Snackbar.make(context,view, getResources().getString(R.string.task_not_saved),Snackbar.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
