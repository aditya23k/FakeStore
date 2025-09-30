package com.example.FakeStore.services;

import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.dtos.fakeStoreDTO;
import com.example.FakeStore.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreService")
public class FakeStoreService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String productRequestUrl="https://fakestoreapi.com/products/{id}";
    private String createProductUrl ="https://fakestoreapi.com/products";

    public FakeStoreService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder= restTemplateBuilder;
    }
    @Override
    public GenericProductDTO getProductById(Long id){
//        return new Product();
//        return null;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreDTO> response=  restTemplate.getForEntity(productRequestUrl, fakeStoreDTO.class, id);
        fakeStoreDTO productDTO= response.getBody();
//        return productDTO.getTitle();
//        response.getStatusCode();
        GenericProductDTO product = new GenericProductDTO();

        product.setImage(productDTO.getImage());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(product.getCategory());

        return product;
    }

    public GenericProductDTO createProduct(GenericProductDTO newproduct){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(createProductUrl,newproduct, GenericProductDTO.class);

        return response.getBody();

    }
}