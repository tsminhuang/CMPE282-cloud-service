package edu.sjsu.cmpe282.service.Impl;


import edu.sjsu.cmpe282.dao.ProjectRepository;
import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.exception.ResourceNotFoundException;
import edu.sjsu.cmpe282.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProjectServiceImpl: implementation all CRUD business logic for Project
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    public static String MSG_NO_ANY_RECORD = "Poject records are empty";
    public static String MSG_TEMPLATE_NOT_EXIST = "Project: with id:{%d} did not exist";
    public static String MSG_TEMPLATE_IS_EXIST = "Project: with id:{%d} already existed";

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

        List<Project> p = repo.findAll();

        if (p.size() == 0) {
            throw new ResourceNotFoundException(MSG_NO_ANY_RECORD);
        }

        return repo.findAll();
    }

    @Override
    public Project findById(Integer id) {
        Project p = repo.findById(id);

        if (p == null) {
            throw new ResourceNotFoundException(
                    String.format(MSG_TEMPLATE_NOT_EXIST, id));
        }

        return p;
    }

    @Override
    public Project update(Project project) {
        return null;
    }
}
