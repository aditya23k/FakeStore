package com.example.FakeStore.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class GetProductTitlesRequestDTO{
    private List<String> uuids;
}