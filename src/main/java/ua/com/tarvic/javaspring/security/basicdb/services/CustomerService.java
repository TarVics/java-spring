package ua.com.tarvic.javaspring.security.basicdb.services;

import ua.com.tarvic.javaspring.security.basicdb.models.Customer;

public interface CustomerService {
    void save(Customer customer);

    Customer findByUsername(String username);

}