package com.pascalschumann.jobshopschedulermicroservice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for the CRUD entites
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
