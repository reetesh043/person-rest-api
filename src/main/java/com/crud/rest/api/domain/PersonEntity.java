package com.crud.rest.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "person")
public class PersonEntity {

    private String firstName;
    private String lastName;
    private String age;
    private String favouriteColour;


    public PersonEntity(String firstName, String lastName, String age, String favouriteColour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
    }

    public PersonEntity() {
     // default constructor
    }

    @Column(name = "age", nullable = false)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Column(name = "favourite_colour", nullable = false)
    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity person = (PersonEntity) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(age, person.age) && Objects.equals(favouriteColour, person.favouriteColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, favouriteColour);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonEntity.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("age='" + age + "'")
                .add("favouriteColour='" + favouriteColour + "'")
                .toString();
    }
}
