package edu.sjsu.cmpe282.restfulnosql.repository;

import edu.sjsu.cmpe282.restfulnosql.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, Integer> {
}
