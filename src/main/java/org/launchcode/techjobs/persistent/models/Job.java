package org.launchcode.techjobs.persistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


//updated class to inherit from AbstractEntity
//removed name and id fields from this class
@Entity
public class Job extends AbstractEntity{

    @ManyToOne
    private Employer employer;
    @ManyToMany
    @NotNull(message = "Skill is required.")
    private List<Skill> skills;

    public Job() {
    }

    public Job(Employer anEmployer, List<Skill>someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
