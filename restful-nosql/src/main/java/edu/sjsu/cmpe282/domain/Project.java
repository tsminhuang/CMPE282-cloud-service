package edu.sjsu.cmpe282.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Project document in MongoDB
 */
@Document(collection = "project")
public class Project {
    public static final int ID_NOT_ASSIGN = -1;
    public static final float BUDGET_NOT_ASSIGN = -1.0f;

    // This is dummy mapping to make mango can work
    @Id
    private String dummyId;

    @Field("id")
    @Indexed(name = "id", unique = true)
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int id;

    @NotNull
    @NotEmpty
    private String name;

    @Min(0)
    private float budget;

    public Project() {
        id = ID_NOT_ASSIGN;
    }

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
