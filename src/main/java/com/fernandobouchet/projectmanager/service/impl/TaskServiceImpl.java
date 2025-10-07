package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.dto.TaskUpdateRequest;
import com.fernandobouchet.projectmanager.model.*;
import com.fernandobouchet.projectmanager.repository.TaskRepository;
import com.fernandobouchet.projectmanager.service.ProjectService;
import com.fernandobouchet.projectmanager.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectService projectService;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }

    @Transactional
    @Override
    public Task createTask(Long userId, Long projectId, String title, String content) {
        Project project = projectService.getProjectIfOwner(userId, projectId);

        Task task = new Task();
        task.setTitle(title);
        task.setContent(content);
        task.setProject(project);

        taskRepository.save(task);

        return task;

    }

    @Transactional
    @Override
    public Task updateTask(Long userId, Long taskId, TaskUpdateRequest requestDto) {
        Task task = getTaskById(userId, taskId);

        requestDto.applyToEntity(task);

        taskRepository.save(task);

        projectService.updateProjectProgress(task.getProject());
        return task;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> listTasksByProject(Long userId, Long projectId) {
        projectService.getProjectIfOwner(userId, projectId);
        return taskRepository.findAllByProjectId(projectId);
    }

    @Transactional(readOnly = true)
    @Override
    public Task getTaskById(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found."));

        projectService.getProjectIfOwner(userId, task.getProject().getId());
        return task;
    }

    @Transactional
    @Override
    public void deleteTask(Long userId, Long taskId) {
        Task task = getTaskById(userId, taskId);
        taskRepository.delete(task);
        projectService.updateProjectProgress(task.getProject());
    }
}
