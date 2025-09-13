package com.fernandobouchet.projectmanager.service.impl;

import com.fernandobouchet.projectmanager.model.*;
import com.fernandobouchet.projectmanager.repository.ProjectRepository;
import com.fernandobouchet.projectmanager.repository.TaskRepository;
import com.fernandobouchet.projectmanager.repository.UserRepository;
import com.fernandobouchet.projectmanager.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Task updateTask(Long taskId, Task task) {
        Task taskToUpdate = getTaskById(taskId);

        if (task.getTitle() != null && !task.getTitle().equals(taskToUpdate.getTitle())) {
            taskToUpdate.setTitle(task.getTitle());
        }
        if (task.getContent() != null && !task.getContent().equals(taskToUpdate.getContent())) {
            taskToUpdate.setContent(task.getContent());
        }
        if (task.getStatus() != null && !task.getStatus().equals(taskToUpdate.getStatus())) {
            taskToUpdate.setStatus(task.getStatus());
        }
        if (task.getPriority() != null && !task.getPriority().equals(taskToUpdate.getPriority())) {
            taskToUpdate.setPriority(task.getPriority());
        }

        taskRepository.save(taskToUpdate);

        Project project = taskToUpdate.getProject();
        project.updateProgress();
        projectRepository.save(project);

        return taskToUpdate;
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
