package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EventCategory extends AbstractEntity {

    @Size(min=3,max=50,message="Name must be between 3-50 characters long")
    private String name;

    EventCategory(@Size(min=3,max=50,message="Name must be between 3-50 characters long") String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return name;
    }
}
