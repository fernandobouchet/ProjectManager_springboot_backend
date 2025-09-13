package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.TaskCreateRequest;
import com.fernandobouchet.projectmanager.dto.TaskResponse;
import com.fernandobouchet.projectmanager.dto.TaskUpdateRequest;
import com.fernandobouchet.projectmanager.model.Task;
import com.fernandobouchet.projectmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public TaskResponse createTask(@RequestBody TaskCreateRequest request) {
        Task task = taskService.createTask(request.getProjectId(), request.getTitle(), request.getContent());

        return TaskResponse.fromEntity(task);
    }

    @GetMapping("/{taskId}")
    public TaskResponse getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);

        return TaskResponse.fromEntity(task);
    }

    @GetMapping("/project/{projectId}")
    public List<TaskResponse> getTasksByProject(@PathVariable Long projectId) {
        List<Task> tasks = taskService.listTasksByProject(projectId);

        return TaskResponse.fromEntities(tasks);
    }

    @PutMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable Long taskId, @RequestBody TaskUpdateRequest request) {
        Task task = request.toEntity();

        Task updatedTask = taskService.updateTask(taskId, task);

        return TaskResponse.fromEntity(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
