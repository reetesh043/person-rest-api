package com.crud.rest.api.service;

import com.crud.rest.api.domain.Person;
import com.crud.rest.api.domain.PersonEntity;
import com.crud.rest.api.exception.ResourceNotFoundException;
import com.crud.rest.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person findByName(String name) {
        PersonEntity personEntity = Optional.ofNullable(personRepository.find(name))
                .orElseThrow(() -> new ResourceNotFoundException("PersonEntity not found with this firstName :: " + name));
        Person person = new Person(personEntity.getFirstName(), personEntity.getLastName(), personEntity.getAge(), personEntity.getFavouriteColour());

        return person;
    }

    @Override
    public List<Person> findAll() {
        List<PersonEntity> personEntityList = personRepository.findAll();

        List<Person> personList = new ArrayList<>();
        personEntityList.stream().forEach(personEntity -> {
            Person person = new Person(personEntity.getFirstName(), personEntity.getLastName(), personEntity.getAge(), personEntity.getFavouriteColour());
            personList.add(person);
        });
        return personList;
    }

    @Override
    public Person update(String name, Person personDetail) {
        PersonEntity personEntity = Optional.ofNullable(personRepository.find(name))
                .orElseThrow(() -> new ResourceNotFoundException("PersonEntity not found with this name :: " + name));

        personEntity.setFirstName(personDetail.getFirstName());
        personEntity.setLastName(personDetail.getLastName());
        personEntity.setAge(personDetail.getAge());
        personEntity.setFavouriteColour(personDetail.getFavouriteColour());
        PersonEntity upDatedPersonEntity = personRepository.save(personEntity);
        return new Person(upDatedPersonEntity.getFirstName(), upDatedPersonEntity.getLastName(), upDatedPersonEntity.getAge(), upDatedPersonEntity.getFavouriteColour());
    }

    @Override
    public Person findByColor(String color) {
        PersonEntity personEntity = Optional.ofNullable(personRepository.find(color))
                .orElseThrow(() -> new ResourceNotFoundException("PersonEntity not found with this color :: " + color));
        return new Person(personEntity.getFirstName(), personEntity.getLastName(), personEntity.getAge(), personEntity.getFavouriteColour());

    }

    @Override
    public void saveAll(List<Person> persons) {
        List<PersonEntity> personEntityList = new ArrayList<>();
        persons.stream().forEach(person -> {
            PersonEntity personEntity = new PersonEntity(person.getFirstName(), person.getLastName(), person.getAge(), person.getFavouriteColour());
            personEntityList.add(personEntity);
        });
        personRepository.saveAll(personEntityList);
    }

    @Override
    public List<Person> deleteByName(String name) {
        List<PersonEntity> personEntityList = Optional.ofNullable(personRepository.delete(name))
                .orElseThrow(() -> new ResourceNotFoundException("PersonEntity not found with this name :: " + name));

        List<Person> personList = new ArrayList<>();
        personEntityList.stream().forEach(personEntity -> {
            Person person = new Person(personEntity.getFirstName(), personEntity.getLastName(), personEntity.getAge(), personEntity.getFavouriteColour());
            personList.add(person);
        });
        return personList;
    }

    @Override
    public List<Person> deleteByColor(String color) {
        List<PersonEntity> personEntityList = Optional.ofNullable(personRepository.delete(color))
                .orElseThrow(() -> new ResourceNotFoundException("PersonEntity not found with this color :: " + color));

        List<Person> personList = new ArrayList<>();
        personEntityList.stream().forEach(personEntity -> {
            Person person = new Person(personEntity.getFirstName(), personEntity.getLastName(), personEntity.getAge(), personEntity.getFavouriteColour());
            personList.add(person);
        });
        return personList;
    }
}
