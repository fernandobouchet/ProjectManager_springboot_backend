package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.model.*;
import com.fernandobouchet.projectmanager.repository.ProjectRepository;
import com.fernandobouchet.projectmanager.repository.TaskRepository;
import com.fernandobouchet.projectmanager.repository.UserRepository;
import com.fernandobouchet.projectmanager.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Task createTask(Long projectId, String title, String content) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

            Task task = new Task();
            task.setTitle(title);
            task.setContent(content);
            task.setProject(project);

            taskRepository.save(task);

            return task;

    }

    @Override
    public Task updateTaskStatus(Long taskId, Status status) {
        Task task = getTaskById(taskId);

        task.setStatus(status);
        taskRepository.save(task);

        Project project = task.getProject();
        project.updateProgress();
        projectRepository.save(project);
        
        return task;
    }

    @Override
    public Task updateTaskPriority(Long taskId, Priority priority) {
        Task task = getTaskById(taskId);

        task.setPriority(priority);
        taskRepository.save(task);

        return task;
    }

    @Override
    public List<Task> listTasksByProject(Long projectId) {
        List<Task> tasks = taskRepository.findAllByProjectId(projectId);
        return tasks;
    }

    @Override
    public Task getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found."));

        return task;
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = getTaskById(taskId);
        taskRepository.delete(task);

        Project project = task.getProject();
        project.updateProgress();
        projectRepository.save(project);
    }
}
