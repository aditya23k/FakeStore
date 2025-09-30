package com.example.FakeStore.models ;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    public Long id;
    public String title;
    public String description;
    public String image;
    public Category category;
    public double price;


}