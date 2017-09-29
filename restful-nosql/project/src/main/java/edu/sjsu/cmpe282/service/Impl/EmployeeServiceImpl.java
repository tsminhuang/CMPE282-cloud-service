package edu.sjsu.cmpe282.service.Impl;

import edu.sjsu.cmpe282.dao.EmployeeRepository;
import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.exception.ErrorMessage;
import edu.sjsu.cmpe282.exception.ResourceCreateException;
import edu.sjsu.cmpe282.exception.ResourceNotFoundException;
import edu.sjsu.cmpe282.service.EmployeeCtxUpdate;
import edu.sjsu.cmpe282.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * EmployeeServiceImpl: implementation all CRUD business logic for Employee
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private EmployeeCtxUpdate ctx;

    @Override
    public Employee create(Employee newCtx) {
        Integer id = newCtx.getId();
        Employee dbCtx = repo.findById(id);

        if (dbCtx != null) {
            throw new ResourceCreateException(ErrorMessage.msgRecordIsExist(id));
        }

        repo.save(newCtx);

        return newCtx;
    }

    @Override
    public Employee delete(Integer id) {
        Employee dbCtx = findById(id);

        if (dbCtx != null) {
            repo.delete(dbCtx);
        }

        return dbCtx;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> dbCollect = repo.findAll();

        if (dbCollect.size() == 0) {
            throw new ResourceNotFoundException(ErrorMessage.msgEmptyCollection());
        }

        return repo.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        Employee dbCtx = repo.findById(id);

        if (dbCtx == null) {
            throw new ResourceNotFoundException(ErrorMessage.msgRecordNotFound(id));
        }

        return dbCtx;
    }

    @Override
    public Employee update(Integer id, Employee newCtx) {
        // Check id is not give in JSON, use requested id
        if (newCtx.getId() == Employee.ID_NOT_ASSIGN) {
            newCtx.setId(id);
        }

        Employee dbCtx = repo.findById(id);
        if (dbCtx == null) {
            throw new ResourceNotFoundException(ErrorMessage.msgRecordNotFound(id));
        }

        if (ctx.update(dbCtx, newCtx)) {
            return repo.save(dbCtx);
        }

        return null;
    }
}
