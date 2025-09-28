package com.fernandobouchet.projectmanager.service;

import com.fernandobouchet.projectmanager.dto.TaskUpdateRequest;
import com.fernandobouchet.projectmanager.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Long userId, Long projectId, String title, String content);
    Task updateTask(Long userId, Long taskId, TaskUpdateRequest requestDto);
    List<Task> listTasksByProject(Long userId, Long projectId);
    Task getTaskById(Long userId, Long taskId);
    void deleteTask(Long userId, Long taskId);
}
