package edu.sjsu.cmpe282.dao;

import edu.sjsu.cmpe282.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ProjectRepository: used MongoRepository generate CRUD for us
 */
public interface ProjectRepository extends MongoRepository<Project, String> {
    Project findById(Integer id);

}
