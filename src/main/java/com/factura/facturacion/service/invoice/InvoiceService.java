package com.factura.facturacion.service.invoice;

import com.factura.facturacion.dto.invoice.DTOInvoice;
import com.factura.facturacion.dto.invoice.DTOResponseInvoice;
import com.factura.facturacion.model.client.Client;
import com.factura.facturacion.model.invoices.Invoice;
import com.factura.facturacion.repository.client.ClientRepository;
import com.factura.facturacion.repository.invoice.InvoiceRepository;
import com.factura.facturacion.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ProductRepository productRepository;

  public Page<DTOResponseInvoice> findAll(Pageable pageable){
    Page<Invoice> invoices = invoiceRepository.findAll(pageable);
    List<DTOResponseInvoice> descInvoices = invoices.stream()
            .sorted(Comparator.comparing(Invoice::getIdInvoice).reversed())
            .map(DTOResponseInvoice::new)
            .toList();
    return new PageImpl<>(descInvoices, pageable, invoices.getTotalElements());
  }

  public DTOResponseInvoice findById(Integer id){
    Optional<Invoice> invoice = invoiceRepository.findById(id);
    if(invoice.isPresent()){
      Invoice i = invoice.get();
      return new DTOResponseInvoice(i.getIdInvoice(), i.getCufeInvoice(), i.getIssueDateInvoice(), i.getIdentifyIssuingInvoice(),
              i.getIdentifyClientInvoice(), i.getClient().getIdClient(), i.getSubtotalValueInvoice(), i.getIvaValueInvoice(), i.getTotalValueInvoice());
    }
    return null;
  }

  public Invoice createInvoice(DTOInvoice dtoInvoice){
    Client client = clientRepository.findById(dtoInvoice.idClientInvoice())
            .orElseThrow(() -> new RuntimeException("Client doesn't exist"));
    if(!dtoInvoice.identifyClientInvoice().equals(client.getIdentifyClient())){
      throw new IllegalArgumentException("ID does not match");
    }
    Invoice invoice = new Invoice(dtoInvoice, client);
    return invoiceRepository.save(invoice);
  }
}
