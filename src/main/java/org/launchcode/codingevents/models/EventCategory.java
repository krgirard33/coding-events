package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEntity {

    @Size(min=3,max=50,message="Name must be between 3-50 characters long")
    private String name;

    EventCategory(@Size(min=3,max=50,message="Name must be between 3-50 characters long") String name) {
        this.name = name;
    }

    public EventCategory() {}

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
