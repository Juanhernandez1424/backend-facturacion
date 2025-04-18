package com.factura.facturacion.dto.invoice;

import com.factura.facturacion.model.client.Client;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DTOInvoice(
        Integer idInvoice,
        String cufeInvoice,
        @NotNull
        Date issueDateInvoice,
        @NotNull
        String identifyIssuingInvoice,
        @NotNull
        String identifyClientInvoice,
        @NotNull
        Integer idClientInvoice,
        @NotNull
        Double subtotalValueInvoice,
        @NotNull
        Double ivaValueInvoice,
        @NotNull
        Double totalValueInvoice
) {
}
