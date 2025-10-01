package com.example.FakeStore.services;

import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.dtos.fakeStoreDTO;
import com.example.FakeStore.exceptions.NotFoundException;
import com.example.FakeStore.models.Product;
import com.nimbusds.oauth2.sdk.GeneralException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service("fakeStoreService")
public class FakeStoreService implements ProductService {

    private RestTemplateBuilder restTemplateBuilder;
    private String productRequestUrl="https://fakestoreapi.com/products/{id}";
    private String createProductUrl ="https://fakestoreapi.com/products";
    private String getAllProductsUrl ="https://fakestoreapi.com/products";
    private String deleteProductRequestUrl="https://fakestoreapi.com/products/{id}";

    public FakeStoreService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder= restTemplateBuilder;
    }

    public GenericProductDTO convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreDTO productDTO){
        GenericProductDTO product = new GenericProductDTO();
        product.setId(product.getId());
        product.setImage(productDTO.getImage());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());

        return product;
    }
    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
//        return new Product();
//        return null;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreDTO> response=  restTemplate.getForEntity(productRequestUrl, fakeStoreDTO.class, id);
        fakeStoreDTO productDTO= response.getBody();
//        return productDTO.getTitle();
//        response.getStatusCode();
        if (productDTO ==null){
            throw new NotFoundException("product with id: "+id +"doesn't exist");
        }
        return convert_fakeStoreDTO_to_GenericProductDTO(productDTO);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO newproduct){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDTO> response = restTemplate.postForEntity(createProductUrl,newproduct, GenericProductDTO.class);

        return response.getBody();

    }
    @Override
    public List<GenericProductDTO> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreDTO[]> response=  restTemplate.getForEntity(getAllProductsUrl, fakeStoreDTO[].class);

        List<GenericProductDTO> productList= new ArrayList<>();
        for( fakeStoreDTO productDTO: Arrays.stream(response.getBody()).toList()){
//        return productDTO.getTitle();
//        response.getStatusCode();

            productList.add(convert_fakeStoreDTO_to_GenericProductDTO(productDTO));
        }
        return productList;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.delete(deleteProductRequestUrl,);
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(fakeStoreDTO.class);
        ResponseExtractor<ResponseEntity<fakeStoreDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(fakeStoreDTO.class);
        ResponseEntity<fakeStoreDTO> response = restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE,requestCallback,responseExtractor,id);

        fakeStoreDTO fakeStoreDTOProduct = response.getBody();

        return convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreDTOProduct);

    }
}