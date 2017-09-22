package edu.sjsu.cmpe282.web.rest;

import edu.sjsu.cmpe282.dao.ProjectRepository;
import edu.sjsu.cmpe282.domain.Employee;
import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private ProjectService service;

    @PostConstruct
    public void buildData() {
        repo.deleteAll();
        repo.save(new Project(1, "A", 1.1f));
        repo.save(new Project(2, "B", 2.2f));
        repo.save(new Project(3, "C", 3.3f));
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Project> getAll() {

        return service.findAll();
    }


}
