package com.example.FakeStore;

import java.util.*;
import com.example.FakeStore.models.Category;
import com.example.FakeStore.models.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import com.example.FakeStore.repositories.ProductRepository;
import com.example.FakeStore.repositories.CategoryRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FakeStoreApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(FakeStoreApplication.class, args);
	}

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;


	public FakeStoreApplication(ProductRepository productRepository, CategoryRepository categoryRepository){
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Apple Devices");
		Category savedCategory = categoryRepository.save(category);

		Product product = new Product();
		product.setTitle("iPhone 15 Pro");
		product.setDescription("The best iPhone Ever");
		product.setCategory(savedCategory);

		productRepository.save(product);

		Category category1 = categoryRepository.findById(UUID.fromString("5e6f679e-f326-44ae-b220-544b3822ab00")).get();
		System.out.println("Category name is: " + category1.getName());
		System.out.println("Printing all products in the category");
		Thread.sleep(1000);
	}
}
