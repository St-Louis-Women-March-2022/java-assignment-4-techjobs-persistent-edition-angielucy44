package org.launchcode.techjobs.persistent.models;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

//AbstractEntity - holds the fields and methods that are shared
//MappedSuperClass means that we are creating tables for the subclasses but
//not for this parent class
@MappedSuperclass
public abstract class AbstractEntity {

    //Fields that are passed down to classes that extend AbstractEntity
    //are id and name
    @Id
    @GeneratedValue
    private int id;

    //added validation annotations to make it a required field enforce a character limit
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    //Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}