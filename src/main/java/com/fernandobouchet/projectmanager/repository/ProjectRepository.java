package com.fernandobouchet.projectmanager.repository;

import com.fernandobouchet.projectmanager.model.Project;
import com.fernandobouchet.projectmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserId(long userId);
}

