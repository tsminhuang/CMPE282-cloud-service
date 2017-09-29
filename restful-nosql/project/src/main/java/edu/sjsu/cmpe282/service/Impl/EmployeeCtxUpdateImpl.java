package edu.sjsu.cmpe282.service.Impl;

import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.exception.ErrorMessage;
import edu.sjsu.cmpe282.exception.ResourceNotFoundException;
import edu.sjsu.cmpe282.service.EmployeeCtxUpdate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCtxUpdateImpl implements EmployeeCtxUpdate {

    @Override
    public boolean update(Employee dbCtx, Employee newCtx) {
        if (dbCtx.getId() != newCtx.getId()) {
            throw new ResourceNotFoundException(
                ErrorMessage.msgNotMatchId(dbCtx.getId(), newCtx.getId()));
        }

        if (newCtx.getFirstName() != null) {
            dbCtx.setFirstName(newCtx.getFirstName());
        }

        if (newCtx.getLastName() != null) {
            dbCtx.setLastName(newCtx.getLastName());
        }

        return true;
    }
}
