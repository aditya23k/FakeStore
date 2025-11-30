package com.example.FakeStore.controller;

import com.example.FakeStore.dtos.ExceptionDTO;
import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.exceptions.NotFoundException;
import com.example.FakeStore.security.JWTObject;
import com.example.FakeStore.security.TokenValidator;
import com.nimbusds.jwt.JWT;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.FakeStore.services.ProductService;
import java.util.*;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {

    private ProductService productService;
    private TokenValidator tokenValidator;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<GenericProductDTO>> getAllProducts() {
        List<GenericProductDTO> productDTOs = productService.getAllProducts();
        if (productDTOs.isEmpty()) {
            return new ResponseEntity<>(
                    productDTOs,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDTO> genericProductDtos = new ArrayList<>();

        for (GenericProductDTO gpd: productDTOs) {
            genericProductDtos.add(gpd);
        };

//        genericProductDtos.remove(genericProductDtos.get(0));

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public GenericProductDTO getProductById(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id) throws NotFoundException{

        System.out.println(authToken);
        Optional<JWTObject> authTokenObjOptional;
        JWT authTokenObj = null;

        if (authToken != null) {
            authTokenObjOptional = tokenValidator.validateToken(authToken);
            if (authTokenObjOptional.isEmpty()) {
                // ignore
            }

            authTokenObj = (JWT) authTokenObjOptional.get();
        }
        GenericProductDTO productDTO = productService.getProductById(id,authTokenObj.getUserId());
        if (productDTO == null) {
            // throw NotFoundException
        }
        return productDTO;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.OK
        );
    }
    @PostMapping()
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
//        System.out.print();
        return productService.createProduct(product);
        //modification
    }
    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }

}

