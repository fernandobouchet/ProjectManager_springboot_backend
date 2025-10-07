package com.fernandobouchet.projectmanager.dto;

import com.fernandobouchet.projectmanager.model.Priority;
import com.fernandobouchet.projectmanager.model.Status;
import com.fernandobouchet.projectmanager.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskUpdateRequest {

    private String title;

    @NotBlank(message = "Task content is mandatory")
    @Size(max = 500, message = "Content cannot exceed 500 characters")
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

    public void applyToEntity(Task task) {
        if (this.title != null && !this.title.equals(task.getTitle())) {
            task.setTitle(this.title);
        }
        if (this.content != null && !this.content.equals(task.getContent())) {
            task.setContent(this.content);
        }
        if (this.status != null && !this.status.equals(task.getStatus())) {
            task.setStatus(this.status);
        }
        if (this.priority != null && !this.priority.equals(task.getPriority())) {
            task.setPriority(this.priority);
        }
    }
}
