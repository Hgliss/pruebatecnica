package com.ventas.gestion_invoice.repository;

import com.ventas.gestion_invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
