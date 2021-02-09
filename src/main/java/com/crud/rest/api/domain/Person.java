package com.crud.rest.api.domain;

import java.util.Objects;
import java.util.StringJoiner;

public class Person {

    private String firstName;
    private String lastName;
    private String age;
    private String favouriteColour;


    public Person(String firstName, String lastName, String age, String favouriteColour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
    }

    public Person() {
        // default constructor
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(age, person.age) && Objects.equals(favouriteColour, person.favouriteColour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, favouriteColour);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("age='" + age + "'")
                .add("favouriteColour='" + favouriteColour + "'")
                .toString();
    }
}

