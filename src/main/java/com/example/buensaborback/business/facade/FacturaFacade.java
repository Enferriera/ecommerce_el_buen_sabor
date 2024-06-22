package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.FacturaDto;
import com.example.buensaborback.domain.entities.Pedido;

import java.io.IOException;

public interface FacturaFacade extends BaseFacade<FacturaDto, FacturaDto, Long> {

    byte[] generarFacturaPDF(Long pedidoId) throws IOException;
}
