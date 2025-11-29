package com.example.FakeStore.thirdpartyclients.ProductService.FakeStore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class FakeStoreProductServiceClientTest {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void testNonExistingProductReturnsNull() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDto = response.getBody();

        assertNull(fakeStoreProductDto);
    }

}
