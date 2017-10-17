package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Project;
import com.astontech.hr.repositories.ProjectRepository;
import com.astontech.hr.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Override
    public Iterable<Project> listAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.findOne(id);
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Iterable<Project> saveProjectList(Iterable<Project> projectIterable) {
        return projectRepository.save(projectIterable);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.delete(id);
    }

    @Autowired
    private ProjectRepository projectRepository;
}
