package com.mksherbini.backend.repos;

import com.mksherbini.backend.models.orm.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CustomerJpaRepo extends JpaRepository<Customer, Integer> {
}
