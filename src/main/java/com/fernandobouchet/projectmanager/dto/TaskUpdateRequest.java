package com.fernandobouchet.projectmanager.dto;

import com.fernandobouchet.projectmanager.model.Priority;
import com.fernandobouchet.projectmanager.model.Status;

public class TaskUpdateRequest {
    private String title;
    private String content;
    private Status status;
    private Priority priority;

    public TaskUpdateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
