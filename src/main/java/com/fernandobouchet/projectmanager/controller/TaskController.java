package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.TaskCreateRequest;
import com.fernandobouchet.projectmanager.dto.TaskResponse;
import com.fernandobouchet.projectmanager.dto.TaskUpdateRequest;
import com.fernandobouchet.projectmanager.model.Task;
import com.fernandobouchet.projectmanager.security.CustomUserDetails;
import com.fernandobouchet.projectmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody TaskCreateRequest request) {
        Long userId = customUserDetails.getId();
        Task task = taskService.createTask(userId, request.getProjectId(), request.getTitle(), request.getContent());

        return ResponseEntity.ok(TaskResponse.fromEntity(task));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long taskId) {
        Long userId = customUserDetails.getId();
        Task task = taskService.getTaskById(userId, taskId);

        return ResponseEntity.ok(TaskResponse.fromEntity(task));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long projectId) {
        Long userId = customUserDetails.getId();
        List<Task> tasks = taskService.listTasksByProject(userId, projectId);

        return ResponseEntity.ok(TaskResponse.fromEntities(tasks));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long taskId, @RequestBody TaskUpdateRequest request) {
        Long userId = customUserDetails.getId();

        Task updatedTask = taskService.updateTask(userId, taskId, request);

        return ResponseEntity.ok(TaskResponse.fromEntity(updatedTask));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long taskId) {
        Long userId = customUserDetails.getId();
        taskService.deleteTask(userId, taskId);

        return ResponseEntity.noContent().build();
    }
}
