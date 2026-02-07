package com.bank.app.service;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updateCustomer) {
        return customerRepository.findById(id)
                .map(existing -> {
                    existing.setFirstName(updateCustomer.getFirstName());
                    existing.setLastName(updateCustomer.getLastName());
                    existing.setEmail(updateCustomer.getEmail());
                    existing.setPhoneNumber(updateCustomer.getPhoneNumber());
                    return customerRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found id" + id));
    }

    public void deleteCustomer(Long id) {
        if(!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id " + id);
        }

        customerRepository.deleteById(id);
    }
}
