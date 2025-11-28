package com.example.FakeStore.models ;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
public class Product extends BaseModel{
    public String title;
    public String description;
    public String image;


    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;


}