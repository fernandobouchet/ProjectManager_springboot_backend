package com.fernandobouchet.projectmanager.dto;

import com.fernandobouchet.projectmanager.model.Priority;
import com.fernandobouchet.projectmanager.model.Status;
import com.fernandobouchet.projectmanager.model.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskUpdateRequest {

    @NotBlank(message = "Project title is mandatory")
    private String title;

    @NotBlank(message = "Project content is mandatory")
    @Size(max = 500, message = "Content cannot exceed 500 characters")
    private String content;

    @NotBlank(message = "Project status is mandatory")
    private Status status;

    @NotBlank(message = "Project priority is mandatory")
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

    public Task toEntity() {
        Task task = new Task();
        task.setTitle(this.title);
        task.setContent(this.content);
        task.setStatus(this.status);
        task.setPriority(this.priority);

        return task;
    }
}
