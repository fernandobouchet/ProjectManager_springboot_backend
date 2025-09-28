package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.exception.UnauthorizedException;
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
    public Project getProject(Long userId, Long id) {
        return getProjectIfOwner(id, userId);
    }

    @Override
    public List<Project> listProjectsByUser(Long userId) {
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public Project updateProjectTitle(Long userId, Long projectId, String title) {
        Project project = getProjectIfOwner(projectId, userId);

        project.setTitle(title);

        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long userId, Long projectId) {
        Project project = getProjectIfOwner(projectId, userId);

        projectRepository.delete(project);
    }

    private Project getProjectIfOwner(Long projectId, Long userId) {
        return projectRepository.findByIdAndUserId(projectId, userId)
                .orElseThrow(() -> new UnauthorizedException("User is not authorized to access the project."));
    }

}
