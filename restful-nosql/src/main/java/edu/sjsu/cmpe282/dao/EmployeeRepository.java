package edu.sjsu.cmpe282.dao;

import edu.sjsu.cmpe282.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * EmployeeRepository: used MongoRepository generate CRUD for us
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
