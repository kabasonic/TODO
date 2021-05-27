package com.kabasonic.todo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private String label;
    private String date;
    private boolean active;

    public Task(String title,String label, String date, boolean active) {
        this.title = title;
        this.label = label;
        this.date = date;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
