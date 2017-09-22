package edu.sjsu.cmpe282.restfulnosql.rest;

import edu.sjsu.cmpe282.restfulnosql.model.Employee;
import edu.sjsu.cmpe282.restfulnosql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @PostConstruct
    public void buildData() {
        repo.deleteAll();
        repo.save(new Employee(1, "A", "a"));
        repo.save(new Employee(2, "B", "b"));
        repo.save(new Employee(3, "C", "c"));
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Employee> getAll() {
        return repo.findAll();
    }
}
