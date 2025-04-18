package com.factura.facturacion.dto.invoice;

import com.factura.facturacion.model.invoices.Invoice;

import java.util.Date;

public record DTOResponseInvoice(
        Integer idInvoice,
        String cufeInvoice,
        Date issueDateInvoice,
        String identifyIssuingInvoice,
        String identifyClientInvoice,
        Integer idClientInvoice,
        Double subtotalValueInvoice,
        Double ivaValueInvoice,
        Double totalValueInvoice
) {
  public DTOResponseInvoice(Invoice invoice){
    this(
            invoice.getIdInvoice(),
            invoice.getCufeInvoice(),
            invoice.getIssueDateInvoice(),
            invoice.getIdentifyIssuingInvoice(),
            invoice.getIdentifyClientInvoice(),
            (invoice.getClient() != null) ? invoice.getClient().getIdClient() : null,
            invoice.getSubtotalValueInvoice(),
            invoice.getIvaValueInvoice(),
            invoice.getTotalValueInvoice()
    );
  }
}
