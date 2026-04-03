package com.dwes.rest.error;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {

        super(message);
    }

    public ProductNotFoundException() {
        super("No hay productos con esos requisitos de busqueda");
    }

    public ProductNotFoundException(Long id) {
        super("No hay producto con ese ID: %d".formatted(id));
    }
}
