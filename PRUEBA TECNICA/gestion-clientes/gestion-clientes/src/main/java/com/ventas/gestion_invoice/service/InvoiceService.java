package com.ventas.gestion_invoice.service;


import com.ventas.gestion_clientes.model.Client;
import com.ventas.gestion_invoice.model.Invoice;
import com.ventas.gestion_invoice.repository.InvoiceRepository;
import com.ventas.gestion_product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceReposity;

    @Autowired
    private RestTemplate restTemplate;

    public Invoice createInvoice(Long clientId, List<Long> productsList){
        //Veriiy client exist
        String clientUrl = "http://localhost:8080/client/" + clientId;
        ResponseEntity<Client   > clientResponse = restTemplate.getForEntity(clientUrl, Client.class);

        if(!clientResponse.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException("Cliente no encontrado");
        }

        //Verify product stock
        productsList.forEach(productId -> {
            String productUrl = "http://localhost:8080/product/" + productId;
            Product product = restTemplate.getForObject(productUrl,Product.class);

            if(product==null || product.getStock()<=0){
                throw new RuntimeException("Producto no disponible o sin stock");
            }
            //Update product stock
            product.setStock(product.getStock()-1);
            restTemplate.put(productUrl,product);
        });

        //create invoice
        Invoice invoice = new Invoice();
        invoice.setClientId(clientId);
        invoice.setProductsList(productsList);

        //caculate total of invoice
        Double total=productsList.stream()
                .map(idPro -> {
                    String url = "http://localhost:8080/product/" + idPro;
                    Product product = restTemplate.getForObject(url, Product.class);
                    return product.getPrice();
                })
                .reduce(0.0,Double::sum);

        invoice.setTotal(total);
        return invoiceReposity.save(invoice);

    }




    public Invoice obtainInvoiceById(Long id){
        return invoiceReposity.findById(id).orElse(null);
    }

    public List<Invoice> obtainAllInvoice(){
        return invoiceReposity.findAll();
    }

}
