package com.crud.rest.api.service;

import com.crud.rest.api.domain.Person;
import com.crud.rest.api.domain.PersonEntity;

import java.util.List;

public interface PersonService {

    Person findByName(String name);

    Person findByColor(String color);

    List<Person> findAll();

    Person update(String name, Person person);

    void saveAll(List<Person> persons);

    List<Person> deleteByName(String name);

    List<Person> deleteByColor(String name);

}
