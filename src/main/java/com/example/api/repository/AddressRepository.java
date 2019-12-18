package com.example.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.api.domain.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
	
}
