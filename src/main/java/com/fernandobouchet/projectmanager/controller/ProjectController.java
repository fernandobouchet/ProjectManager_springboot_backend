package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.ProjectCreateRequest;
import com.fernandobouchet.projectmanager.dto.ProjectResponse;
import com.fernandobouchet.projectmanager.dto.ProjectUpdateRequest;
import com.fernandobouchet.projectmanager.model.Project;
import com.fernandobouchet.projectmanager.security.CustomUserDetails;
import com.fernandobouchet.projectmanager.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody ProjectCreateRequest request) {
        Long userId = customUserDetails.getId();

        Project project = projectService.createProject(userId, request.getTitle());

        return new ResponseEntity<>(ProjectResponse.fromEntity(project), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable("projectId") Long projectId) {
        Long userId = customUserDetails.getId();

        Project project = projectService.getProject(userId, projectId);

        return ResponseEntity.ok(ProjectResponse.fromEntity(project));
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponse>> getProjectByUserId(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getId();

        List<Project> projects = projectService.listProjectsByUser(userId);

        return ResponseEntity.ok(ProjectResponse.fromEntities(projects));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProjectTitle(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable("projectId") Long projectId, @RequestBody ProjectUpdateRequest request) {
        Long userId = customUserDetails.getId();

        Project project = projectService.updateProjectTitle(userId, projectId, request.getTitle());

        return ResponseEntity.ok(ProjectResponse.fromEntity(project));
    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable("projectId") Long projectId) {
        Long userId = customUserDetails.getId();

        projectService.deleteProject(userId, projectId);

        return ResponseEntity.noContent().build();
    }
}
