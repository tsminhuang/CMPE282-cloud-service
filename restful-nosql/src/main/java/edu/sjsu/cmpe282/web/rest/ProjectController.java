package edu.sjsu.cmpe282.web.rest;

import edu.sjsu.cmpe282.dao.ProjectRepository;
import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public List<Project> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "{id}", method =  RequestMethod.DELETE)
    public Project delete(@PathVariable("id") int id)
    {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project ctxCreated,
                          HttpServletRequest request, HttpServletResponse response) {
        Project projectCreated = service.create(ctxCreated);
        response.setHeader("Location",
                request.getRequestURL().append("/").append(projectCreated.getId()).toString());

        return projectCreated;
    }

}
