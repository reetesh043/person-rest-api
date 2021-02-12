package com.crud.rest.api.controller;


import com.crud.rest.api.model.Person;
import com.crud.rest.api.model.Request;
import com.crud.rest.api.model.Response;
import com.crud.rest.api.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@DisplayName("Person Service Test")
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PersonService service;


    @Test
    @DisplayName("When all persons are requested then they are all returned")
    void allPersonsRequested() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(get("/persons")).andExpect(status().isOk()).andReturn();
        Response response = mapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertEquals(6, response.getPerson().size());
    }

    @Test
    @DisplayName("When a person creation is requested then it is persisted")
    void personCreatedCorrectly() throws Exception {
        Person newPerson = new Person("R", "K", "30", "red");
        List<Person> personList = new ArrayList<>();
        personList.add(newPerson);

        Long newPersonId =
                mapper
                        .readValue(
                                mockMvc
                                        .perform(
                                                post("/persons")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(mapper.writeValueAsString(new Request(personList))))
                                        .andExpect(status().isCreated())
                                        .andReturn()
                                        .getResponse()
                                        .getContentAsString(),
                                Response.class).getPerson().get(0)
                        .getId();

        newPerson.setId(newPersonId); // Populate the ID of the person after successful creation

        assertThat(
                Optional.ofNullable(service
                        .getPersonById(newPersonId))
                        .orElseThrow(
                                () -> new IllegalStateException("New Person has not been saved in the repository")),
                equalTo(newPerson));
    }

    @Test
    @DisplayName("When a person update is requested then it is updated")
    void personUpdatedCorrectly() throws Exception {
        Person updatePerson = new Person("R", "K", "30", "red");
        List<Person> personList = new ArrayList<>();
        personList.add(updatePerson);
        Long updatePersonId = mapper
                .readValue(
                        mockMvc
                                .perform(
                                        put("/persons/1")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(mapper.writeValueAsString(new Request(personList))))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        Response.class).getPerson().get(0).getId();
        updatePerson.setId(updatePersonId); // Populate the ID of the person after successful update

        assertThat(
                Optional.ofNullable(service
                        .getPersonById(updatePersonId))
                        .orElseThrow(
                                () -> new IllegalStateException("New Person has not been updated in the repository")),
                equalTo(updatePerson));
    }

    @Test
    @DisplayName("When a person delete is requested then it is deleted")
    void personDeletedCorrectly() throws Exception {
        mockMvc
                .perform(
                        delete("/persons/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}

