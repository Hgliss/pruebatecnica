package com.ventas.gestion_clientes.repository;

import com.ventas.gestion_clientes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Client, Long> {
}
