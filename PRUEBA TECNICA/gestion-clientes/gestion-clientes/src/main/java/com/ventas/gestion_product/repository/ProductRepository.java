package com.ventas.gestion_product.repository;

import com.ventas.gestion_product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
