package com.astontech.hr.repositories;

import com.astontech.hr.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
