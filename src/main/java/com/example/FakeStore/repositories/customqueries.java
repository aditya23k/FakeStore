package com.example.FakeStore;

public interface customqueries {
    String FIND_ALL_BY_TITLE = "select * from product join product_orders " +
            "on product.id = product_orders.product_id where title = :";
}