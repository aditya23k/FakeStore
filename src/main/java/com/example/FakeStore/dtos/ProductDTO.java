package com.example.FakeStore.dtos;

import com.example.FakeStore.models.Category;
import com.example.FakeStore.models.Price;
import lombok.Getter;
import lombok.Setter;

public class ProductDTO {
    private String title;

    private String description;

    private String image;

    private Price price;

    private Category category;

}