package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoCreateDto;
import com.example.buensaborback.domain.dto.pedidoDto.PedidoDto;

public interface PedidoFacade extends BaseFacade <PedidoDto, PedidoDto, Long> {
    public PedidoDto create(PedidoCreateDto pedidoDto);
}
