package com.fernandobouchet.projectmanager.dto;

import com.fernandobouchet.projectmanager.model.Priority;
import com.fernandobouchet.projectmanager.model.Status;
import com.fernandobouchet.projectmanager.model.Task;

import java.util.Date;
import java.util.List;

public class TaskResponse {
    private Long id;
    private String title;
    private String content;
    private Status status;
    private Priority priority;
    private Date createdAt;
    private Date updatedAt;

    public TaskResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static TaskResponse fromEntity(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setContent(task.getContent());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }

    public static List<TaskResponse> fromEntities(List<Task> tasks) {
        return tasks.stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }
}
