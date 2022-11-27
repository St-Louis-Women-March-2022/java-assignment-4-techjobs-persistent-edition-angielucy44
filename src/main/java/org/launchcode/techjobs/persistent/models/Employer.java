package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    //added location field & added validations to make it required and enforce
    //a character limit
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    @NotBlank(message = "Location is required.")
    private String location;

    @OneToMany
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();

    //Added getters and setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //Added no arg constructor required for Hibernate to create an object
    public Employer() {}

}
