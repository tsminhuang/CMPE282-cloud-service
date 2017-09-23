package edu.sjsu.cmpe282.service.Impl;

import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.exception.ErrorMessage;
import edu.sjsu.cmpe282.exception.ResourceNotFoundException;
import edu.sjsu.cmpe282.service.ProjectCtxUpdate;
import org.springframework.stereotype.Service;

@Service
public class ProjectCtxUpdateImpl implements ProjectCtxUpdate {
    @Override
    public boolean update(Project dbCtx, Project newCtx) {

        if (dbCtx.getId() != newCtx.getId()) {
            throw new ResourceNotFoundException(
                    ErrorMessage.msgNotMatchId(dbCtx.getId(), newCtx.getId()));
        }

        if (newCtx.getBudget() != Project.BUDGET_NOT_ASSIGN) {
            dbCtx.setBudget(newCtx.getBudget());
        }

        if (newCtx.getName() != null) {
            dbCtx.setName(newCtx.getName());
        }

        return true;
    }
}
