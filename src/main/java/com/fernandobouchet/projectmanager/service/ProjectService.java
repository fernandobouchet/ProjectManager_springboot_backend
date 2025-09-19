package com.fernandobouchet.projectmanager.service;

import com.fernandobouchet.projectmanager.model.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Long userId, String title);
    Project getProject(Long userId, Long projectId);
    List<Project> listProjectsByUser(Long userId);
    Project updateProjectTitle(Long userId, Long projectId, String title);
    void deleteProject(Long userId, Long projectId);
}
