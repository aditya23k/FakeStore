
package com.example.FakeStore.services;

import java.util.*;
import com.example.FakeStore.dtos.GenericProductDTO;
import com.example.FakeStore.exceptions.NotFoundException;
import com.example.FakeStore.models.Product;
import com.example.FakeStore.security.*;

public interface ProductService {
    GenericProductDTO getProductById(Long id, Long userIdTryingToAccess) throws NotFoundException;

    GenericProductDTO createProduct(GenericProductDTO productDTO);

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO deleteProductById(Long id);
}