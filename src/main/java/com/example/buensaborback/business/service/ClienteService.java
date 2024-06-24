package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.clienteDto.ClienteCreateDto;
import com.example.buensaborback.domain.entities.Cliente;

public interface ClienteService extends BaseService<Cliente, Long> {
    public Cliente findClienteByUsuarioClienteId(Long id);

    public Cliente createCliente(Cliente cliente);
}
