package edu.sjsu.cmpe282.restfulnosql.repository;

import edu.sjsu.cmpe282.restfulnosql.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
}
