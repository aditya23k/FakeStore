package com.example.FakeStore.security;

import java.util.*;

public class JWTObject {
    private String email;
    private Long userId;
    private Date createdAt;
    private Date expiryAt;
    private List<Role> roles = new ArrayList<>();


}