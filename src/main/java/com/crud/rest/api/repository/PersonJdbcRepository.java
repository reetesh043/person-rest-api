package com.crud.rest.api.repository;

import com.crud.rest.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a new person
    public int addPerson(String firstName, String lastName, String age, String favouriteColour) {
        String sql = "INSERT INTO person (first_name, last_name, age, favourite_colour) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, firstName, lastName, age, favouriteColour);
    }

    // Find all persons
    public List<Person> findAll() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new PersonRowMapper());
    }

    // Find a person by id
    public Person findById(Long id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PersonRowMapper(), id);
    }

    // Delete a person by id
    public int deleteById(Long id) {
        String sql = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // RowMapper to map result set to Person object
    private static class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person p = new Person();
            p.setId(rs.getLong("id"));
            p.setFirst_name(rs.getString("first_name"));
            p.setLast_name(rs.getString("last_name"));
            p.setAge(rs.getString("age"));
            p.setFavourite_colour(rs.getString("favourite_colour"));
            return p;
        }
    }
}

