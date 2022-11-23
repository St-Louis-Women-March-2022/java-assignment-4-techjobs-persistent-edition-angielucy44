package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    @NotBlank(message = "Location is required.")
    private String location;

    public String getLocation() {
        return location;
    }

    public Employer() {}

    public void setLocation(String location) {
        this.location = location;
    }
}
