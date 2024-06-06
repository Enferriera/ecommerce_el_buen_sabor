package com.example.buensaborback.business.facade;


import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.domain.entities.PreferenceMP;

public interface MercadoPagoFacade {
    public PreferenceMP crearPreference(PedidoDto dto);
}
