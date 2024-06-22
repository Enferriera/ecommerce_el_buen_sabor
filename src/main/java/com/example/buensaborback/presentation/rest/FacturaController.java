package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.business.facade.Imp.FacturaFacadeImpl;
import com.example.buensaborback.domain.dto.FacturaDto;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/facturas")
@CrossOrigin("*")
public class FacturaController extends BaseControllerImp<Factura, FacturaDto,FacturaDto,Long, FacturaFacadeImpl> {
    private final FacturaFacadeImpl facturaFacadeImpl;

    public FacturaController(FacturaFacadeImpl facade, FacturaFacadeImpl facturaFacadeImpl) {
        super(facade);
        this.facturaFacadeImpl = facturaFacadeImpl;
    }


    @GetMapping("/facturaPdf/{pedidoId}")
    public ResponseEntity<byte[]> descargarFactura(@PathVariable Long pedidoId) throws IOException {

        byte[] pdfContent = facturaFacadeImpl.generarFacturaPDF(pedidoId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + pedidoId + ".pdf");
        headers.setContentLength(pdfContent.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
