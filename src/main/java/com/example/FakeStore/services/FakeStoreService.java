package com.example.FakeStore.services;

import com.example.FakeStore.dtos.fakeStoreDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreService")
public class FakeStoreService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String productRequestUrl="https://fakestoreapi.com/products/{id}";

    public FakeStoreService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder= restTemplateBuilder;
    }
    @Override
    public String getProductById(Long id){
//        return new Product();
//        return null;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreDTO> response=  restTemplate.getForEntity(productRequestUrl,fakeStoreDTO.class, id);

//        response.getStatusCode();
        return "Here is the product: " +id;
    }

}