package com.crud.rest.api.repository;

import com.crud.rest.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The Interface PersonRepository exposes the method to retrieve data from database.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
