package com.fernandobouchet.projectmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectCreateRequest {

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
}
