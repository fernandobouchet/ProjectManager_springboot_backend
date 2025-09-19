package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.ProjectCreateRequest;
import com.fernandobouchet.projectmanager.dto.ProjectResponse;
import com.fernandobouchet.projectmanager.dto.ProjectUpdateRequest;
import com.fernandobouchet.projectmanager.model.Project;
import com.fernandobouchet.projectmanager.security.JwtUtil;
import com.fernandobouchet.projectmanager.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final JwtUtil jwtUtil;

    public ProjectController(ProjectService projectService, JwtUtil jwtUtil) {
        this.projectService = projectService;
        this.jwtUtil = jwtUtil;
    }

    private Long extractUserId(String authHeader) {
        return jwtUtil.getUserIdFromHeader(authHeader);
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestHeader("Authorization") String authHeader, @RequestBody ProjectCreateRequest request) {
        Long userId = extractUserId(authHeader);

        Project project = projectService.createProject(userId, request.getTitle());

        return new ResponseEntity<>(ProjectResponse.fromEntity(project), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@RequestHeader("Authorization") String authHeader, @PathVariable("projectId") Long projectId) {
        Long userId = extractUserId(authHeader);

        Project project = projectService.getProject(userId, projectId);

        return ResponseEntity.ok(ProjectResponse.fromEntity(project));
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponse>> getProjectByUserId(@RequestHeader("Authorization") String authHeader) {
        Long userId = extractUserId(authHeader);

        List<Project> projects = projectService.listProjectsByUser(userId);

        return ResponseEntity.ok(ProjectResponse.fromEntities(projects));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProjectTitle(@RequestHeader("Authorization") String authHeader, @PathVariable("projectId") Long projectId, @RequestBody ProjectUpdateRequest request) {
        Long userId = extractUserId(authHeader);

        Project project = projectService.updateProjectTitle(userId, projectId, request.getTitle());

        return ResponseEntity.ok(ProjectResponse.fromEntity(project));
    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@RequestHeader("Authorization") String authHeader, @PathVariable("projectId") Long projectId) {
        Long userId = extractUserId(authHeader);

        projectService.deleteProject(userId, projectId);

        return ResponseEntity.noContent().build();
    }
}
