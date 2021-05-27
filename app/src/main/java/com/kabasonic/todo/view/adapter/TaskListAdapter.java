package com.kabasonic.todo.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        void onClickActiveTask(long id, boolean active);
    }

    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    private Context context;
    private List<Task> taskList;

    public TaskListAdapter(Context context) {
        this.context = context;
        this.taskList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.title.setText(task.getTitle());
        holder.label.setText(task.getLabel());
        holder.date.setText(String.valueOf(task.getDate()));
        holder.active.setChecked(task.isActive());
        if (task.isActive()) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.label.setPaintFlags(holder.label.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.date.setPaintFlags(holder.date.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.title.setPaintFlags(0);
            holder.label.setPaintFlags(0);
            holder.date.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount() {
        if (taskList == null) {
            return 0;
        }
        return taskList.size();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView label;
        public TextView date;
        public MaterialCheckBox active;


        public ViewHolder(@NonNull View itemView, OnItemClickListener mListener) {
            super(itemView);

            title = itemView.findViewById(R.id.titleRowId);
            label = itemView.findViewById(R.id.labelRowId);
            date = itemView.findViewById(R.id.dataRowId);
            active = itemView.findViewById(R.id.statusRowId);

            itemView.setOnClickListener(v -> {
                long id = taskList.get(getAdapterPosition()).getId();
                    mListener.onClickItemTask(id);
            });

            active.setOnClickListener(v -> {
                boolean activeStatus = active.isChecked();
                active.setChecked(activeStatus);
                long id = taskList.get(getAdapterPosition()).getId();
                mListener.onClickActiveTask(id, activeStatus);
            });
        }
    }
}
