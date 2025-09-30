package com.example.FakeStore.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/products/")
public class ProductController {
    @GetMapping("")
    public void getAllProducts(){

    }
    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id){
        return "Here is the product:" +id;
    }
    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") Long id){

    }
    @PostMapping()
    public String createProduct(){
        return "created a new Product with the code: "+ UUID.randomUUID();
    }
    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}

