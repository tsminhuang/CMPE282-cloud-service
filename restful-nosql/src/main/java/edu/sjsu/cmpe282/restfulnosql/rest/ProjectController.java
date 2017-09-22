package edu.sjsu.cmpe282.restfulnosql.rest;

import edu.sjsu.cmpe282.restfulnosql.model.Project;
import edu.sjsu.cmpe282.restfulnosql.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    List<Project> getAll() {
        return repo.findAll();
    }
}
