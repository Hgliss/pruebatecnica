package com.ventas.gestion_product.service;


import com.ventas.gestion_product.model.Product;
import com.ventas.gestion_product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productReposity;

    //Create or Update
    public Product saveProduct(Product product){
        return productReposity.save(product);
    }

    //Read All
    public List<Product> obtainAllClient(){
        return productReposity.findAll();
    }

    //Read product by Id
    public Product obtainProductById(Long id){
        Optional<Product> product = productReposity.findById(id);
        return product.orElse(null);
    }

    //Delete
    public void deleteProduct(Long id){
        productReposity.deleteById(id);
    }

}
