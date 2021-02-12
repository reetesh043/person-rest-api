package com.crud.rest.api.controller;

import com.crud.rest.api.exception.ResourceNotFoundException;
import com.crud.rest.api.model.Person;
import com.crud.rest.api.model.Request;
import com.crud.rest.api.model.Response;
import com.crud.rest.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This is controller class for exposing RESTful endpoints. This class receives incoming request,
 * validates the headers, body and delegates the call to service implementation. It returns the
 * response from the service implementation back to the calling client
 */

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;


    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAllPersons() {

        return new Response(personService.getAllPersons());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getPersonById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Person person = personService.getPersonById(id);
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        return new Response(personList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createPerson(@Valid @RequestBody Request request) {
        return new Response(personService.create(request.getPerson()));
    }

    @PutMapping("/{id}")
    public Response updatePerson(@PathVariable(value = "id") Long personId,
                                 @Valid @RequestBody Request request) {

        Person person = personService.updatePerson(personId, request.getPerson());
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        return new Response(personList);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(value = "id") Long personId) {
        personService.deletePersonById(personId);
    }
}
