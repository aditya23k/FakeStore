package com.example.FakeStore.dtos;

import com.example.FakeStore.models.Category;
import com.example.FakeStore.models.Price;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class fakeStoreDTO{

    public int id;
    public String title;
    public Price price;
    public Category category;
    public String description;
    public String image;


}
