package com.fernandobouchet.projectmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskCreateRequest {

    @NotNull(message = "Project ID is mandatory")
    private Long projectId;

    @NotBlank(message = "Task title is mandatory")
    private String title;

    @NotBlank(message = "Task content is mandatory")
    @Size(max = 500, message = "Content cannot exceed 500 characters")
    private String content;

    public TaskCreateRequest() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
}
