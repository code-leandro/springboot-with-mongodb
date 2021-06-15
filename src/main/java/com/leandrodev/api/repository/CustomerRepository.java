package com.leandrodev.api.repository;

import com.leandrodev.api.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String firstName);
}
