package com.crud.rest.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String age;
    private String favourite_colour;

    public Person() {
        //default constructor
    }

    public Person(String first_name, String last_name, String age, String favourite_colour) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.favourite_colour = favourite_colour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavourite_colour() {
        return favourite_colour;
    }

    public void setFavourite_colour(String favourite_colour) {
        this.favourite_colour = favourite_colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(first_name, person.first_name) && Objects.equals(last_name, person.last_name) && Objects.equals(age, person.age) && Objects.equals(favourite_colour, person.favourite_colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, age, favourite_colour);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("first_name='" + first_name + "'")
                .add("last_name='" + last_name + "'")
                .add("age='" + age + "'")
                .add("favourite_colour='" + favourite_colour + "'")
                .toString();
    }
}

