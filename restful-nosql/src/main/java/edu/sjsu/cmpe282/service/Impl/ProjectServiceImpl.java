package edu.sjsu.cmpe282.service.Impl;

import edu.sjsu.cmpe282.dao.ProjectRepository;
import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.exception.ErrorMessage;
import edu.sjsu.cmpe282.exception.ResourceCreateException;
import edu.sjsu.cmpe282.exception.ResourceNotFoundException;
import edu.sjsu.cmpe282.service.ProjectCtxUpdate;
import edu.sjsu.cmpe282.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProjectServiceImpl: implementation all CRUD business logic for Project
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private ProjectCtxUpdate ctx;

    @Override
    public Project create(Project newCtx) {
        Integer id = newCtx.getId();
        Project dbCtx = repo.findById(id);

        if (dbCtx != null) {
            throw new ResourceCreateException(ErrorMessage.msgRecordIsExist(id));
        }

        repo.save(newCtx);

        return newCtx;
    }

    @Override
    public Project delete(Integer id) {
        Project dbCtx = findById(id);

        if (dbCtx != null) {
            repo.delete(dbCtx);
        }

        return dbCtx;
    }

    @Override
    public List<Project> findAll() {
        List<Project> dbCollect = repo.findAll();

        if (dbCollect.size() == 0) {
            throw new ResourceNotFoundException(ErrorMessage.msgEmptyCollection());
        }

        return repo.findAll();
    }

    @Override
    public Project findById(Integer id) {
        Project dbCtx = repo.findById(id);

        if (dbCtx == null) {
            throw new ResourceNotFoundException(ErrorMessage.msgRecordNotFound(id));
        }

        return dbCtx;
    }

    @Override
    public Project update(Integer id, Project newCtx) {
        // Check id if give in JSON, if not use requested id
        if (newCtx.getId() == Project.ID_NOT_ASSIGN) {
            newCtx.setId(id);
        }

        Project dbCtx = repo.findById(id);
        if (dbCtx == null) {
            throw new ResourceNotFoundException(ErrorMessage.msgRecordNotFound(id));
        }

        if (ctx.update(dbCtx, newCtx)) {
            return repo.save(dbCtx);
        }

        return null;
    }
}
