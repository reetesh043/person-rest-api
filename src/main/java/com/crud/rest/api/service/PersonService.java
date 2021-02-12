package com.crud.rest.api.service;

import com.crud.rest.api.model.Person;
import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(Long personId);

    List<Person> create(List<Person> personList);

    void deletePersonById(Long personId);

    Person updatePerson(Long personId, List<Person> person);
}
