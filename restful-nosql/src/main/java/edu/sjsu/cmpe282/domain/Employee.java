package edu.sjsu.cmpe282.domain;

import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Employee document object in MongoDB
 */
@Document(collection = "employee")
public class Employee {

    public static final int ID_NOT_ASSIGN = -1;

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
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    public Employee() {
        id = ID_NOT_ASSIGN;
    }

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id
            && Objects.equals(dummyId, employee.dummyId)
            && Objects.equals(firstName, employee.firstName)
            && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dummyId, id, firstName, lastName);
    }
}
