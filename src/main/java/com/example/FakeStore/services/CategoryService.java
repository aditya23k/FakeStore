package com.example.FakeStore.services;

import com.example.FakeStore.models.Category;

import java.util.*;

public interface CategoryService {
    Category getCategory(String uuid);
    List<String> getProductTitles(List<String> categoryUUIDs);
}
