package com.crud.rest.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.crud.rest.api.model.Person;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveAndFindById() {
        Person person = new Person();
        person.setFirst_name("John");
        person.setLast_name("Doe");
        person.setAge("30");
        person.setFavourite_colour("Blue");

        Person saved = personRepository.save(person);

        Optional<Person> found = personRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getFirst_name()).isEqualTo("John");
        assertThat(found.get().getLast_name()).isEqualTo("Doe");
        assertThat(found.get().getAge()).isEqualTo("30");
        assertThat(found.get().getFavourite_colour()).isEqualTo("Blue");
    }

    @Test
    @Sql("/data.sql")
    void testFindAll() {


        List<Person> allPeople = personRepository.findAll();

        assertThat(allPeople)
                .hasSize(2)
                .extracting(Person::getFirst_name)
                .containsExactlyInAnyOrder("Alice", "Bob");
    }

    @Test
    @Sql(scripts = "/data.sql")
    @SqlMergeMode(SqlMergeMode.MergeMode.OVERRIDE) // Ensures ONLY this script runs
    void testFindAllWithSixPeople() {
        List<Person> allPeople = personRepository.findAll();

        assertThat(allPeople)
                .hasSize(6)
                .extracting(Person::getFirst_name)
                .containsExactlyInAnyOrder(
                        "Reetesh",
                        "David",
                        "John",
                        "Paul",
                        "Tom",
                        "Jyoti"
                );
    }

    @Test
    void testDeleteById() {
        Person p = new Person( "Delete", "Me", "45", "Yellow");
        p = personRepository.save(p);

        personRepository.deleteById(p.getId());

        Optional<Person> deleted = personRepository.findById(p.getId());
        assertThat(deleted).isNotPresent();
    }
}

