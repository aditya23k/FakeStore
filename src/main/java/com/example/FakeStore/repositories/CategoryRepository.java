package com.example.FakeStore.repositories;

import com.example.FakeStore.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Lazy
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}