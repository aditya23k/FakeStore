package com.example.FakeStore.controller;

import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import static java.lang.reflect.Array.get;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerWebMVCTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().is(404))
                .andExpect(content().string("[]"));
    }

    @Test
    void returnsListOfProductsWhenProductsExist() throws Exception {
        ArrayList<GenericProductDTO> products = new ArrayList<>();
        products.add(new GenericProductDTO());
        products.add(new GenericProductDTO());
        products.add(new GenericProductDTO());

        when(
                productService.getAllProducts()
        ).thenReturn(
                products
        );

        mockMvc.perform(
                get("/products")
        ).andExpect(
                status().is(200)
        ).andExpect(
                content().string(objectMapper.writeValueAsString(products))
        );
    }

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        GenericProductDTO productToCreate = new GenericProductDTO();
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImage("some image");
        productToCreate.setCategory("mobile phones");
        productToCreate.setDescription("Best iPhone Ever");

        GenericProductDTO expectedProduct = new GenericProductDTO();
        expectedProduct.setId(1001L);
        expectedProduct.setTitle("iPhone 15 Pro Max");
        expectedProduct.setImage("some image");
        expectedProduct.setCategory("mobile phones");
        expectedProduct.setDescription("Best iPhone Ever");

        when(
                productService.createProduct(any())
        ).thenReturn(
                expectedProduct
        );

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreate))
        ).andExpect(
                content().string(objectMapper.writeValueAsString(expectedProduct))
                ).andExpect(status().is(200))
                .andExpect(jsonPath("$.student.name", is("Naman")))
                .andExpect(jsonPath("$.length()", is(2)));
    }

}
