package com.example.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.api.domain.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
	List<Address> findAllByCustomerId(Long id);
}
