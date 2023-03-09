package org.launchcode.codingevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class EventCategory {
    @Id
    @GeneratedValue
    private int id;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventCategory that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
