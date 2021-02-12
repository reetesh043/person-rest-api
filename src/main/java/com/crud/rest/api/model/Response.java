package com.crud.rest.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Valid
public final class Response implements Serializable {

    private static final long serialVersionUID = 4667880509788829403L;

    private final List<Person> person;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Response(@JsonProperty("person") final List<Person> person) {
        this.person = person;
    }

    public List<Person> getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("person=" + person)
                .toString();
    }
}
