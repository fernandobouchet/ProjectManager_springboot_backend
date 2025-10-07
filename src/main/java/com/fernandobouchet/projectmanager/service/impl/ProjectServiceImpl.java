package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.exception.UnauthorizedException;
import com.fernandobouchet.projectmanager.model.Project;
import com.fernandobouchet.projectmanager.model.User;
import com.fernandobouchet.projectmanager.repository.ProjectRepository;
import com.fernandobouchet.projectmanager.repository.UserRepository;
import com.fernandobouchet.projectmanager.service.ProjectService;
import com.fernandobouchet.projectmanager.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Project createProject(Long userId, String title) {
        User user = userService.findById(userId);

        Project project = new Project();
        project.setTitle(title);
        project.setUser(user);

        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    @Override
    public Project getProject(Long userId, Long id) {
        return getProjectIfOwner(userId, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> listProjectsByUser(Long userId) {
        return projectRepository.findAllByUserId(userId);
    }

    @Transactional
    @Override
    public Project updateProjectTitle(Long userId, Long projectId, String title) {
        Project project = getProjectIfOwner(userId, projectId);

        project.setTitle(title);

        return projectRepository.save(project);
    }

    @Transactional
    @Override
    public void deleteProject(Long userId, Long projectId) {
        Project project = getProjectIfOwner(userId, projectId);

        projectRepository.delete(project);
    }

    @Transactional(readOnly = true)
    @Override
    public Project getProjectIfOwner(Long userId, Long projectId) {
        return projectRepository.findByIdAndUserId(projectId, userId)
                .orElseThrow(() -> new UnauthorizedException("User is not authorized to access the project."));
    }

    @Transactional
    @Override
    public void updateProjectProgress(Project project) {
        project.updateProgress();
        projectRepository.save(project);
    }
}
