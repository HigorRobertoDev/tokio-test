package com.example.api.web.rest;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Address;
import com.example.api.domain.Cep;
import com.example.api.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	/*
	@PostMapping
	public Address saveAddressByCEP(@RequestBody Cep cep) {
		return addressService.saveAddressByCep(cep);
	}*/
	
	/*@GetMapping("/{cep}")
	public Cep getCep(@PathVariable String cep) {
		return addressService.getCepByViaCep(cep);
	}*/
	
}
