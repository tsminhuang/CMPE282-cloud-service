package edu.sjsu.cmpe282.web.rest;

import edu.sjsu.cmpe282.dao.ProjectRepository;
import edu.sjsu.cmpe282.domain.Project;
import edu.sjsu.cmpe282.service.ProjectService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository repo;

    @Autowired
    private ProjectService service;

    //    @PostConstruct
    //    public void buildData() {
    //        repo.deleteAll();
    //        repo.save(new Project(1, "A", 1.1f));
    //        repo.save(new Project(2, "B", 2.2f));
    //        repo.save(new Project(3, "C", 3.3f));
    //    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Project> getAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Project findById(@PathVariable @Min(0) @Max(Integer.MAX_VALUE) int id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Project delete(@PathVariable @Min(0) @Max(Integer.MAX_VALUE) int id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(
        @RequestBody @Valid Project newCtx,
        HttpServletRequest request,
        HttpServletResponse response) {
        Project projectCreated = service.create(newCtx);
        response.setHeader(
            "Location",
            request.getRequestURL().append("/").append(projectCreated.getId()).toString());

        return projectCreated;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Project update(
        @PathVariable @Min(0) @Max(Integer.MAX_VALUE) int id, @RequestBody Project newCtx) {
        return service.update(id, newCtx);
    }
}
