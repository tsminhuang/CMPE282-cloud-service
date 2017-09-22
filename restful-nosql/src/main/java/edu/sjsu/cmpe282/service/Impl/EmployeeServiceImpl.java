package edu.sjsu.cmpe282.service.Impl;

import edu.sjsu.cmpe282.dao.EmployeeRepository;
import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.exception.ResourceConflictException;
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

    public static String MSG_NO_ANY_RECORD = "Employee records are empty";
    public static String MSG_TEMPLATE_NOT_EXIST = "Employee with id:{%d} did not exist";
    public static String MSG_TEMPLATE_IS_EXIST = "Employee with id:{%d} already existed";


    @Autowired
    private EmployeeRepository repo;

    @Override
    public Employee create(Employee newEmployee) {
        Integer id = newEmployee.getId();
        Employee e = repo.findById(id);

        if (e != null) {
            throw new ResourceConflictException(
                    String.format(MSG_TEMPLATE_IS_EXIST, id));
        }

        repo.save(newEmployee);

        return newEmployee;
    }

    @Override
    public Employee delete(Integer id) {

        Employee e = findById(id);

        if (e != null) {
            repo.delete(e);
        }

        return e;
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
    public Employee findById(Integer id) {
        Employee e = repo.findById(id);

        if (e == null) {
            throw new ResourceNotFoundException(
                    String.format(MSG_TEMPLATE_NOT_EXIST, id));
        }

        return e;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }
}
