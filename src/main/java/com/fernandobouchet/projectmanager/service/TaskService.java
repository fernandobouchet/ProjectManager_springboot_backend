package com.fernandobouchet.projectmanager.service;

import com.fernandobouchet.projectmanager.model.Priority;
import com.fernandobouchet.projectmanager.model.Status;
import com.fernandobouchet.projectmanager.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Long projectId, String title, String content);
    Task updateTaskStatus(Long taskId, Status status);
    Task updateTaskPriority(Long taskId, Priority priority);
    List<Task> listTasksByProject(Long projectId);
    Task getTaskById(Long taskId);
    void deleteTask(Long taskId);
}
