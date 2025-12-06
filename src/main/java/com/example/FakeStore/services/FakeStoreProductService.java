package com.example.FakeStore.services;


import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.dtos.fakeStoreDTO;
import com.example.FakeStore.exceptions.NotFoundException;
import com.example.FakeStore.security.JWTObject;

import com.example.FakeStore.models.Product;
import com.example.FakeStore.thirdpartyclients.ProductService.FakeStore.FakeStoreProductDTO;
import com.example.FakeStore.thirdpartyclients.ProductService.FakeStore.FakeStoreProductServiceClient;
import com.nimbusds.oauth2.sdk.GeneralException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Primary
@Repository("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;


    public GenericProductDTO convert_fakeStoreDTO_to_GenericProductDTO(FakeStoreProductDTO productDTO){
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

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDTO getProductById(Long id, Long userIdTryingAccess) throws NotFoundException {
//        return new Product();
//        return null;
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<fakeStoreDTO> response=  restTemplate.getForEntity(productRequestUrl, fakeStoreDTO.class, id);
//        fakeStoreDTO productDTO= response.getBody();
////        return productDTO.getTitle();
////        response.getStatusCode();
//        if (productDTO ==null){
//            throw new NotFoundException("product with id: "+id +"doesn't exist");
//        }
//        return convert_fakeStoreDTO_to_GenericProductDTO(productDTO);
        System.out.println("In product service");
        return convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO newproduct){
        return convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreProductServiceClient.createProduct(newproduct));
    }
    @Override
    public List<GenericProductDTO> getAllProducts(){


        List<GenericProductDTO> productList= new ArrayList<>();
        for (FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductServiceClient.getAllProducts()) {
            productList.add(convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreProductDTO));
        }
        return productList;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
////        restTemplate.delete(deleteProductRequestUrl,);
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(fakeStoreDTO.class);
//        ResponseExtractor<ResponseEntity<fakeStoreDTO>> responseExtractor =
//                restTemplate.responseEntityExtractor(fakeStoreDTO.class);
//        ResponseEntity<fakeStoreDTO> response = restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE,requestCallback,responseExtractor,id);
//
//        fakeStoreDTO fakeStoreDTOProduct = response.getBody();
//
//        return convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreDTOProduct);

        return convert_fakeStoreDTO_to_GenericProductDTO(fakeStoreProductServiceClient.deleteProduct(id));

    }
}