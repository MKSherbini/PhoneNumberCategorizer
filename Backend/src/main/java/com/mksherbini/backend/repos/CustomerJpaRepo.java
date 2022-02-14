package com.mksherbini.backend.repos;

import com.mksherbini.backend.models.orm.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepo extends JpaRepository<Customer, Integer> {
}
