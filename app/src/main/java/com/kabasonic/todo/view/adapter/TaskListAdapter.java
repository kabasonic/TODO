package com.kabasonic.todo.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.kabasonic.todo.R;
import com.kabasonic.todo.data.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private Context context;
    private List<Task> taskList;

    public TaskListAdapter(Context context, List<Task> taskList){
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.task_list_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.label.setText(task.getLabel());
        holder.date.setText(String.valueOf(task.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        if(taskList == null){
            return 0;
        }
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView label;
        public TextView date;
        public MaterialCheckBox active;
        public ImageButton expendedButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleRowId);
            description = itemView.findViewById(R.id.descRowId);
            label = itemView.findViewById(R.id.labelRowId);
            date = itemView.findViewById(R.id.dataRowId);
            active = itemView.findViewById(R.id.statusRowId);
            expendedButton = itemView.findViewById(R.id.btRowId);
        }
    }
}
