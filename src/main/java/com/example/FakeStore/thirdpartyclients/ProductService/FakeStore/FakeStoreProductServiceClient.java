package com.example.FakeStore.thirdpartyclients.ProductService.FakeStore;

import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.exceptions.NotFoundException;
import lombok.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;


    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl;
    private String productRequestsBaseUrl;

    public FakeStoreProductServiceClient (RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestsBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }


    public FakeStoreProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                productRequestsBaseUrl, product, FakeStoreProductDTO.class
        );

        return response.getBody();
    }

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
//        FakeStoreProductService fakeStoreProductService = new FakeStoreProductService();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        if (fakeStoreProductDTO == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }

//        response.getStatusCode()

        return fakeStoreProductDTO;
//        return null;
    }

    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO[]> response =
                restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDTO[].class);

        List<GenericProductDTO> answer = new ArrayList<>();

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeStoreProductDTO deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }
}