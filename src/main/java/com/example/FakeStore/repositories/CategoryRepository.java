package com.example.FakeStore.repositories;

import com.example.FakeStore.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Lazy
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);

}