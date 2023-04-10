package org.food.truck.ordering.domain.ports.output.repository;

import org.food.truck.ordering.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID customerId);
}