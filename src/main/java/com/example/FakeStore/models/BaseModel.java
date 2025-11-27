package com.example.FakeStore.models;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;
import java.util.UUID;

@MappedSuperclass
public class BaseModel{
    private Long id;
    @Id
    @GeneratedValue(generator="aditya")
    @GenericGenerator(name= "aditya", strategy ="uuid2")
    @Column(name ="id",columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;

}