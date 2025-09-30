package com.example.FakeStore.controller;

import com.example.FakeStore.dtos.GenericProductDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.example.FakeStore.services.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public void getAllProducts(){

    }
    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") Long id){

    }
    @PostMapping()
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
//        System.out.print();
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}

