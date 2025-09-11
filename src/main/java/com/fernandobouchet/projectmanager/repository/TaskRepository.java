package com.fernandobouchet.projectmanager.repository;

import com.fernandobouchet.projectmanager.model.Status;
import com.fernandobouchet.projectmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStatus(Status status);
    List<Task> findByProjectIdAndStatus(Long projectId, Status status);
    List<Task> findAllByProjectId(Long projectId);
}

