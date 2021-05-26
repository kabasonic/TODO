package com.kabasonic.todo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    public interface OnItemClickListener {

        void onClickItemTask(long id);

    }

    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }

    private Context context;
    private List<Task> taskList;

    public TaskListAdapter(Context context){
        this.context = context;
        this.taskList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_list_item,parent,false);
        return new ViewHolder(view,mListener);
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

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView label;
        public TextView date;
        public MaterialCheckBox active;
        public ImageButton expendedButton;

        public ViewHolder(@NonNull View itemView, OnItemClickListener mListener) {
            super(itemView);

            title = itemView.findViewById(R.id.titleRowId);
            description = itemView.findViewById(R.id.descRowId);
            label = itemView.findViewById(R.id.labelRowId);
            date = itemView.findViewById(R.id.dataRowId);
            active = itemView.findViewById(R.id.statusRowId);
            expendedButton = itemView.findViewById(R.id.btRowId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickItemTask(taskList.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
