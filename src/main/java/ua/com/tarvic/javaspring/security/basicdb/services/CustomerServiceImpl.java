package ua.com.tarvic.javaspring.security.basicdb.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.tarvic.javaspring.security.basicdb.dao.CustomerDAO;
import ua.com.tarvic.javaspring.security.basicdb.models.Customer;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(Customer customer) {
        String password = customer.getPassword();
        String encode = passwordEncoder.encode(password);
        customer.setPassword(encode);
        customerDAO.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerDAO.findByUsername(username);
    }
}