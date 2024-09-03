package com.ventas.gestion_product.dtop;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApiResponseP<P> {
    @Setter
    private String message;
    private final P data;

    public ApiResponseP(String message, P datos) {
        this.message = message;
        this.data = datos;
    }
}
