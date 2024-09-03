package com.ventas.gestion_product.controller;


import com.ventas.gestion_clientes.dto.ApiResponse;
import com.ventas.gestion_product.dtop.ApiResponseP;
import com.ventas.gestion_product.model.Product;
import com.ventas.gestion_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Read all product
    @GetMapping
    public List<Product> obtainAllProduct(){
        return productService.obtainAllClient();
    }

    //Read product by Id
    @GetMapping("/{id}")
    public Product obtainProductById(@PathVariable Long id){
        return productService.obtainProductById(id);
    }

    //Create new product
    @PostMapping
    public Product saveProduct (@RequestBody Product product){
        return productService.saveProduct(product);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseP<Product>> updateProduct(@PathVariable Long id, @RequestBody Product detailsProduct) {
        Product existingProduct = productService.obtainProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(detailsProduct.getName());
            existingProduct.setDescription(detailsProduct.getName());
            existingProduct.setPrice(Double.parseDouble(detailsProduct.getName()));
            existingProduct.setStock(Integer.parseInt(detailsProduct.getName()));
            Product updatedProduct = productService.saveProduct(existingProduct);
            return ResponseEntity.ok(new ApiResponseP<>("Producto actualizado con éxito", updatedProduct));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseP<>("Cliente no encontrado", null));
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }










}
