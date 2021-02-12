package com.crud.rest.api.service;

import com.crud.rest.api.exception.BadRequestException;
import com.crud.rest.api.exception.ResourceNotFoundException;
import com.crud.rest.api.model.Person;
import com.crud.rest.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        Optional<List<Person>> personList = Optional.ofNullable(personRepository.findAll().stream()
                .collect(Collectors.toList()));
        if (personList.isPresent() && !personList.get().isEmpty()) {
            return personList.get();
        } else {
            throw new ResourceNotFoundException("No person record found in database");
        }
    }

    @Override
    public Person getPersonById(Long personId) {
        if (personId == null) {
            throw new BadRequestException("The person ID must not be null");
        }
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new ResourceNotFoundException("No person found with given person id");
        }

    }

    @Override
    public List<Person> create(List<Person> persons) {
        persons.stream().forEach(person -> {
            if (person.getId() != null) {
                throw new BadRequestException("The ID must not be provided when creating a new Person");
            }
        });
        List<Person> personList = personRepository.saveAll(persons);
        if (personList.size() != 0) {
            return personList;
        } else {
            throw new RuntimeException("Not able to create person to database");
        }
    }

    @Override
    public void deletePersonById(Long personId) {
        if (personId == null) {
            throw new BadRequestException("The person ID must not be null");
        }
        Optional<Person> person = personRepository.findById(personId);

        if (person.isPresent()) {
            personRepository.deleteById(personId);
        } else {
            throw new ResourceNotFoundException("No person record exist for given id to delete");
        }

    }

    @Override
    public Person updatePerson(Long personId, List<Person> personList) {
        if (personId == null || personList.size() == 0 || personList.isEmpty()) {
            throw new BadRequestException("The person detail to be updated must not be null ");
        }

        Optional<Person> personDetail = personRepository.findById(personId);
        if (personDetail.isPresent()) {
            Person personObj = new Person();
            personObj = personDetail.get();
            personObj.setFirst_name(personList.get(0).getFirst_name());
            personObj.setLast_name(personList.get(0).getLast_name());
            personObj.setAge(personList.get(0).getAge());
            personObj.setFavourite_colour(personList.get(0).getFavourite_colour());
            return personRepository.save(personObj);
        } else {
            throw new ResourceNotFoundException("No person record exist for given id to update");
        }
    }
}
