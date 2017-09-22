package edu.sjsu.cmpe282.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * Project doucment in MongoDB
 */

@Document(collection = "project")
public class Project {

    // This is dummy mapping to make mango can work
    @Id
    private String dummyId;

    @Field("id")
    @Indexed(name = "id", unique = true)
    private int id;

    private String name;
    private float budget;

    public Project(int id, String name, float budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                Float.compare(project.budget, budget) == 0 &&
                Objects.equals(dummyId, project.dummyId) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dummyId, id, name, budget);
    }
}
