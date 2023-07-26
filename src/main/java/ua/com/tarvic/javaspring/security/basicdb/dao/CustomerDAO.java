package ua.com.tarvic.javaspring.security.basicdb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.tarvic.javaspring.security.basicdb.models.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);
}