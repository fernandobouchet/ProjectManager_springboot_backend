package com.fernandobouchet.projectmanager.dto;

public class ProjectCreateRequest {
    private String title;
    private Long userId;

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
