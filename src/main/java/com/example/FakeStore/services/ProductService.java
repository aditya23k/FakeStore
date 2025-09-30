
package com.example.FakeStore.services;

import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.models.Product;

public interface ProductService {
    GenericProductDTO getProductById(Long id);
}