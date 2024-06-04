package com.Catering_Server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Catering_Server.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
