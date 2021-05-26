package com.kabasonic.todo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;
    private String description;
    private String label;
    private long timestamp;
    private boolean active;

    public Task(String title, String description, String label, long timestamp, boolean active) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.timestamp = timestamp;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
