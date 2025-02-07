package com.vlms.stock.exception;

public class ProductServiceLogicException extends Exception{
    public ProductServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}
