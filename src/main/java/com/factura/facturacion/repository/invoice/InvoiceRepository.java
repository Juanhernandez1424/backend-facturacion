package com.factura.facturacion.repository.invoice;

import com.factura.facturacion.model.invoices.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
