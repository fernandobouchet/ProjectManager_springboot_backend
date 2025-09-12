package com.fernandobouchet.projectmanager.dto;

import com.fernandobouchet.projectmanager.model.Project;

import java.util.Date;
import java.util.List;

public class ProjectResponse {
    private Long id;
    private String title;
    private Double progress;
    private Date createdAt;
    private Date updatedAt;

    public ProjectResponse() {
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

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
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

    public static ProjectResponse fromEntity(Project project) {
        ProjectResponse dto = new ProjectResponse();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setProgress(project.getProgress());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());
        return dto;
    }

    public static List<ProjectResponse> fromEntities(List<Project> projects) {
        return projects.stream()
                .map(ProjectResponse::fromEntity)
                .toList();
    }
}
