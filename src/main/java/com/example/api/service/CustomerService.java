package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

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
	
	public Optional<Customer> saveCustomer(Customer input) {
		Customer customer = repository.save(input);
		
		List<Address> addresses = new ArrayList<>();
		input.getAddresses().stream()
			.forEach(ad -> {
				Address address = new Address();
				address.setNameAddress(ad.getNameAddress());
				address.setNeighborhood(ad.getNeighborhood());
				address.setCity(ad.getCity());
				address.setCountry(ad.getCountry());
				address.setCpf(ad.getCpf());
				address.setCustomer(customer);
				addresses.add(address);
			});
		addressRepository.saveAll(addresses);
		List<Address> addresses2 = addressRepository.findAllByCustomerId(customer.getId());
		customer.setAddresses(addresses2);
		
		return repository.findById(customer.getId());
	}
	
	public void deleteCustumer(Long id) {
		repository.deleteById(id);
	}

}
