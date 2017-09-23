package edu.sjsu.cmpe282.web.rest;

import edu.sjsu.cmpe282.dao.EmployeeRepository;
import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private EmployeeService service;

//    @PostConstruct
//    public void buildData() {
//        repo.deleteAll();
//        repo.save(new Employee(1, "A", "a"));
//        repo.save(new Employee(2, "B", "b"));
//        repo.save(new Employee(3, "C", "c"));
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee findById(@PathVariable
                             @Min(0) @Max(Integer.MAX_VALUE) int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Employee delete(@PathVariable
                           @Min(0) @Max(Integer.MAX_VALUE) int id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee newCtx,
                           HttpServletRequest request, HttpServletResponse response) {
        Employee employeeCreated = service.create(newCtx);
        response.setHeader("Location",
                request.getRequestURL().append("/").append(employeeCreated.getId()).toString());

        return employeeCreated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Employee update(
            @PathVariable
            @Min(0) @Max(Integer.MAX_VALUE) int id,
            @RequestBody Employee newCtx) {
        return service.update(id, newCtx);
    }
}
