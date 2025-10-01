package com.example.FakeStore.dtos;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class fakeStoreDTO{

    public int id;
    public String title;
    public double price;
    public String category;
    public String description;
    public String image;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() {
        return image;
    }
    public void setImage(String image) { this.image = image; }
}
