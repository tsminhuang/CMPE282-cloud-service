package edu.sjsu.cmpe282.service.Impl;


import edu.sjsu.cmpe282.dao.ProjectRepository;
import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectServiceImpl: implementation all CRUD business logic for Project
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repo;

    @Override
    public Project create(Project project) {
        return null;
    }

    @Override
    public Project delete(Integer integer) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return repo.findAll();
    }

    @Override
    public Project findById(Integer integer) {
        return null;
    }

    @Override
    public Project update(Project project) {
        return null;
    }
}
