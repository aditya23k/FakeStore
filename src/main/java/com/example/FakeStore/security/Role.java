package com.example.FakeStore.security;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Role{
    private Long id;
    private String role;
}