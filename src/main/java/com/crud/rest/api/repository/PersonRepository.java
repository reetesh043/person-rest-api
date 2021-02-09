package com.crud.rest.api.repository;

import com.crud.rest.api.domain.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Interface PersonRepository exposes the method to retrieve data from database.
 */
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, String> {

    /**
     * Fetch person detail  based by name, color.
     *
     * @param name - name, color
     * @return person data
     */
    PersonEntity find(String name);

    /**
     * Fetch all persons detail.
     *
     * @return list of person data
     */
    List<PersonEntity> findAll();

    /**
     * save all persons detail.
     *
     * @param persons - list of person detail
     */
    void saveAll(List<PersonEntity> persons);

    /**
     * save single person detail.
     *
     * @param person - person detail
     * @return person
     */
    PersonEntity save(PersonEntity person);

    /**
     * Delete person detail  based by name, color.
     *
     * @param name - name, color
     * @return list of person
     */
    List<PersonEntity> delete(String name);

}
