package com.bookRegister.bookRegister.loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bookRegister.bookRegister.entity.Customer;
import com.bookRegister.bookRegister.repository.CustomerRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public DatabaseLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        if (customerRepository.count() == 0) {
            Customer customer1 = new Customer(1,"Cagan Eren", "cagane@gma.com");
            Customer customer2 = new Customer(2,"Deniz", "deniz@gmal.com");
            Customer customer3 = new Customer(3,"Ali", "ali@hot.com");
            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
        }
    }
}
