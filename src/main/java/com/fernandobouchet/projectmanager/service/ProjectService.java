package com.fernandobouchet.projectmanager.service;

import com.fernandobouchet.projectmanager.model.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Long userId, String title);
    Project getProject(Long projectId);
    List<Project> listProjectsByUser(Long userId);
    Project updateProjectTitle(Long id, String title);
    void deleteProject(Long id);
}
