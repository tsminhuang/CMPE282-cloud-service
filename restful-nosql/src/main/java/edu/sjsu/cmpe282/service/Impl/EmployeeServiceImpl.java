package edu.sjsu.cmpe282.service.Impl;

import edu.sjsu.cmpe282.dao.EmployeeRepository;
import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.exception.ResourceNotFoundException;
import edu.sjsu.cmpe282.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EmployeeServiceImpl: implementation all CRUD business logic for Employee
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public Employee create(Employee employee) {
        return null;
    }

    @Override
    public Employee delete(Integer integer) {
        return null;
    }

    @Override
    public List<Employee> findAll() {

        List<Employee> e = repo.findAll();

        if (e.size() == 0) {
            throw new ResourceNotFoundException("No Employee record");
        }

        return repo.findAll();
    }

    @Override
    public Employee findById(Integer integer) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }
}
