package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.model.Project;
import com.fernandobouchet.projectmanager.model.User;
import com.fernandobouchet.projectmanager.repository.ProjectRepository;
import com.fernandobouchet.projectmanager.repository.UserRepository;
import com.fernandobouchet.projectmanager.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project createProject(Long userId, String title) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        Project project = new Project();
        project.setTitle(title);
        project.setUser(user);

        return projectRepository.save(project);
    }

    @Override
    public Project getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        return project;
    }

    @Override
    public List<Project> listProjectsByUser(Long userId) {
        List<Project> projects = projectRepository.findAllByUserId(userId);

        return projects;
    }

    @Override
    public Project updateProjectTitle(Long id, String title) {
        Project project = getProject(id);

        project.setTitle(title);

        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = getProject(id);

        projectRepository.delete(project);
    }

}
