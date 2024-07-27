package com.librarysystem.service;

import com.librarysystem.model.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String CEP_API_URL = "https://viacep.com.br/ws/{cep}/json/";

    public Address getAddressByCep(String cep) {
        return restTemplate.getForObject(CEP_API_URL, Address.class, cep);
    }
}
