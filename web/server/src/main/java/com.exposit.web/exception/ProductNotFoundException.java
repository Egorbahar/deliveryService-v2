package com.exposit.web.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(Long id) {
       super(String.format("Product with id %d not found",id));
    }

}
