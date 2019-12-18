package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}
	
	public Page<Customer> findAll(Integer numberPage, Integer size, Boolean asc, String fieldOrderBy) {
		PageRequest pageable = new PageRequest(numberPage, size, asc ? Sort.Direction.ASC : Sort.Direction.DESC, fieldOrderBy);
		Page<Customer> page = repository.findAll(pageable);
		return page;
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public Customer saveCustomer(Customer input) {
		return repository.save(input);
	}
	
	public void deleteCustumer(Long id) {
		repository.deleteById(id);
	}

}
