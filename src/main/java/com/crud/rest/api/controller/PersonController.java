package com.crud.rest.api.controller;

import com.crud.rest.api.domain.Person;
import com.crud.rest.api.domain.PersonEntity;
import com.crud.rest.api.exception.ResourceNotFoundException;
import com.crud.rest.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;

/**
 * This is controller class for exposing RESTful endpoints. This class receives incoming request,
 * validates the headers, body and delegates the call to service implementation. It returns the
 * response from the service implementation back to the calling client
 */

@RestController
@Validated
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping(value = "/persons/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Person getPersonByName(@PathVariable(value = "name") String name)
            throws ResourceNotFoundException {

        return personService.findByName(name);

    }

    @PostMapping(value = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@Valid @RequestBody List<Person> personList) {

        personService.saveAll(personList);
    }

    @PutMapping(value = "/persons/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Person updatePersonByName(@PathVariable(value = "name") String name, @Valid @RequestBody Person personDetail) {
        return personService.update(name, personDetail);
    }

    @DeleteMapping(value = "/persons/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> deletePersonByName(@PathVariable(value = "name") String name) {
        return personService.deleteByName(name);

    }
}
