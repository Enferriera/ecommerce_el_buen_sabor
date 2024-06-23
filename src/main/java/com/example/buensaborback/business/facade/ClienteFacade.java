package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.clienteDto.ClienteDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteShortDto;

public interface ClienteFacade extends BaseFacade<ClienteDto, ClienteDto, Long> {

    public ClienteShortDto obtenerClientePorUsuarioClienteId(Long id);
}
