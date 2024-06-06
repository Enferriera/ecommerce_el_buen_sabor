package com.example.buensaborback.business.facade;


import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;
import com.example.buensaborback.presentation.rest.PreferenceMP;

public interface MercadoPagoFacade {
    public PreferenceMP crearPreference(PedidoDto dto);
}
