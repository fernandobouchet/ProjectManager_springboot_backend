package com.fernandobouchet.projectmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectCreateRequest {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotBlank(message = "Title is mandatory")
    @Size(max=  100, message = "Title cant exceed 100 characters")
    private String title;

    public ProjectCreateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
