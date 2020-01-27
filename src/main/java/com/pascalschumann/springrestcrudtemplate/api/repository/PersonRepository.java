package com.pascalschumann.springrestcrudtemplate.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for the CRUD entites
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
