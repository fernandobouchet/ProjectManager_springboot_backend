package com.fernandobouchet.projectmanager.controller;

import com.fernandobouchet.projectmanager.dto.ProjectCreateRequest;
import com.fernandobouchet.projectmanager.dto.ProjectResponse;
import com.fernandobouchet.projectmanager.dto.ProjectUpdateRequest;
import com.fernandobouchet.projectmanager.model.Project;
import com.fernandobouchet.projectmanager.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/")
    public ProjectResponse createProject(@RequestBody ProjectCreateRequest request) {
        Project project = projectService.createProject(request.getUserId(), request.getTitle());

        return ProjectResponse.fromEntity(project);
    }

    @GetMapping("/{projectId}")
    public ProjectResponse getProjectById(@PathVariable("projectId") Long projectId) {
        Project project = projectService.getProject(projectId);

        return ProjectResponse.fromEntity(project);
    }

    @GetMapping("/user/{userId}")
    public List<ProjectResponse> getProjectByUserId(@PathVariable("userId") Long userId) {
        List<Project> projects = projectService.listProjectsByUser(userId);

        return ProjectResponse.fromEntities(projects);
    }

    @PutMapping("/{projectId}")
    public ProjectResponse updateProjectTitle(@PathVariable("projectId") Long projectId, @RequestBody ProjectUpdateRequest request) {
        Project project = projectService.updateProjectTitle(projectId, request.getTitle());

        return ProjectResponse.fromEntity(project);
    }


    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
