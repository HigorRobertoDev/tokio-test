package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Address;
import com.example.api.domain.Cep;
import com.example.api.repository.AddressRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	public Gson gson = new GsonBuilder().serializeNulls().create();
	
	public Cep getCepByViaCep(String cep) {
		return gson.fromJson(createClientResponse("https://viacep.com.br/ws/"+cep+"/json/"), Cep.class) ;
	}
	
	public Address getCepByCep(String cepInput) {
		Cep cep = getCepByViaCep(cepInput);
		
		Address address = new Address();
		address.setNameAddress(cep.getLogradouro());
		address.setNeighborhood(cep.getBairro());
		address.setCity(cep.getLocalidade());
		address.setCountry("Brasil");
		address.setCpf(cep.getCep());
		
		return address;
	}
	
	public String createClientResponse(String url) {
        // String paramFipeJson = gson.toJson(paramFipe);
        Client client = Client.create();
        WebResource webResource = client
                .resource(url);

        ClientResponse response = webResource
                .type("application/json")
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Connection" , "keep-alive")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-origin")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : Http error code: "
                    + response.getStatus());
        }

        return response.getEntity(String.class);
    }
	
}
