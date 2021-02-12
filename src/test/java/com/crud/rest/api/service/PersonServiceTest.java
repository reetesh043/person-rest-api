package com.crud.rest.api.service;

import com.crud.rest.api.exception.BadRequestException;
import com.crud.rest.api.exception.ResourceNotFoundException;
import com.crud.rest.api.model.Person;
import com.crud.rest.api.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Person Service Test")
class PersonServiceTest {

    @InjectMocks
    PersonServiceImpl personService;

    @Mock
    PersonRepository personRepository;

    @Test
    @DisplayName("Get all persons from repository")
    public void getAllPersons() {
        List<Person> expectedPersons = Arrays.asList(new Person("R", "K", "25", "red"),
                new Person("P", "Q", "25", "blue"));
        when(personRepository.findAll()).thenReturn(expectedPersons);
        List<Person> actualResponse = personService.getAllPersons();
        assertEquals(expectedPersons.size(), actualResponse.size());
        assertArrayEquals(expectedPersons.toArray(), actualResponse.toArray());
    }

    @Test
    @DisplayName("Create new person in repository")
    public void createNewPerson() {
        Person expectedPerson = new Person("R", "K", "25", "red");
        List<Person> persons = Arrays.asList(expectedPerson);
        when(personRepository.saveAll(anyList())).thenReturn(persons);
        List<Person> actualResponse = personService.create(persons);
        assertEquals(persons.size(), actualResponse.size());
        assertArrayEquals(persons.toArray(), actualResponse.toArray());
    }

    @Test
    @DisplayName("Update existing person in repository")
    public void updateExistingPerson() {
        Person expectedPerson = new Person("R", "K", "25", "red");
        expectedPerson.setId(1L);
        List<Person> personList = Arrays.asList(expectedPerson);
        when(personRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expectedPerson));
        when(personRepository.save(any())).thenReturn(expectedPerson);
        Person actualResponse = personService.updatePerson(1L, personList);
        assertEquals(expectedPerson.getAge(), actualResponse.getAge());
        assertEquals(expectedPerson.getFirst_name(), actualResponse.getFirst_name());
        assertEquals(expectedPerson.getFavourite_colour(), actualResponse.getFavourite_colour());
        assertEquals(expectedPerson.getLast_name(), actualResponse.getLast_name());
    }

    @Test
    @DisplayName("Delete existing person in repository")
    public void deleteExistingPerson() {
        Person expectedPerson = new Person("R", "K", "25", "red");
        expectedPerson.setId(1L);
        when(personRepository.findById(anyLong())).thenReturn(Optional.ofNullable(expectedPerson));
        personService.deletePersonById(1L);
        verify(personRepository, atLeast(1)).deleteById(anyLong());

    }


    @Test
    @DisplayName("Throw Exception while deleting existing person in repository if id null")
    public void throwExceptionWhileDeletingExistingPersonIfIdIsNull() {
        Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
            personService.deletePersonById(null);
        });
        String expectedMessage = "The person ID must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throw Exception while deleting existing person in repository if no record found")
    public void throwExceptionWhileDeletingExistingPersonIfNoExistingRecordFound() {
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            personService.deletePersonById(1L);
        });
        String expectedMessage = "No person record exist for given id to delete";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throw Exception while getting existing person in repository if id is null")
    public void throwExceptionWhileGettingExistingPersonIfIdIsNull() {
        Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
            personService.getPersonById(null);
        });
        String expectedMessage = "The person ID must not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throw Exception while getting existing person in repository if no record found")
    public void throwExceptionWhileGettingExistingPersonIfNoExistingRecordFound() {
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            personService.getPersonById(1L);
        });
        String expectedMessage = "No person found with given person id";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
