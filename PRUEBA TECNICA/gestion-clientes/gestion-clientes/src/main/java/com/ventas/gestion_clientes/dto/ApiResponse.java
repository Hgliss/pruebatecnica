package com.ventas.gestion_clientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApiResponse <T>{
    @Setter
    private String message;
    private final T data;

    public ApiResponse(String message, T datos) {
        this.message = message;
        this.data = datos;
    }

}
