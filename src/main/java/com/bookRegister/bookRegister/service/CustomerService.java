package com.bookRegister.bookRegister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRegister.bookRegister.entity.Customer;
import com.bookRegister.bookRegister.repository.CustomerRepository;

@Service
public class CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;

	public Customer getCustomerById(int id) {
		return customerRepo.findById(id).get();
	}
}