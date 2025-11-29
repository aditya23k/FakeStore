package com.example.FakeStore.controller;

import com.example.FakeStore.dtos.GetProductTitlesRequestDTO;
import com.example.FakeStore.dtos.ProductDTO;
import com.example.FakeStore.dtos.fakeStoreDTO;
import com.example.FakeStore.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.FakeStore.models.Product;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

public class CategoryController{
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{uuid}")
    public List<ProductDTO> getCategory(@PathVariable("uuid") String uuid) {
        List<Product> products = categoryService.getCategory(uuid).getProducts();

        List<ProductDTO> productDtos = new ArrayList<>();

        for (Product product: products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setDescription(product.getDescription());
            productDTO.setTitle(product.getTitle());

            productDtos.add(productDTO);
        }
        return productDtos;
    }
    @GetMapping("/titles/")
    public List<String> getProductTitles(@RequestBody GetProductTitlesRequestDTO requestDto) {

        List<String> uuids = requestDto.getUuids();

        return categoryService.getProductTitles(uuids);
    }
}