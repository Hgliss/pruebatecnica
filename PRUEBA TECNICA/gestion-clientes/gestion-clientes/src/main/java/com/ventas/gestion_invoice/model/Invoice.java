package com.ventas.gestion_invoice.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;
    private Long clientId;

    @ElementCollection
    private List<Long> productsList;
    private Double total;

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Long> productsList) {
        this.productsList = productsList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
