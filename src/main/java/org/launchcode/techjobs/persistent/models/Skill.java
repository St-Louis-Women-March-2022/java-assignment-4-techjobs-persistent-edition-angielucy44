package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    //Added description field with longer character limit
    @Size(max=500, message = "Description cannot be longer than 500 characters.")
    private String description;

    //Added list of jobs as a field
    //Added ManyToMany annotation bc many jobs can have more than one skill
    @ManyToMany(mappedBy="skills")
    @NotNull
    private List<Job> jobs = new ArrayList<>();

    //Added getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    //Added no arg constructor
    public Skill() {}

}