package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.clienteDto.ClienteCreateDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;

public interface ClienteFacade extends BaseFacade<ClienteDto, ClienteDto, Long> {

    public ClienteShortDto obtenerClientePorUsuarioClienteId(Long id);

    public ClienteDto createCliente(ClienteCreateDto cliente);
}
