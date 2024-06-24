package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.ClienteFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.ClienteMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.domain.dto.clienteDto.ClienteCreateDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteDto;
import com.example.buensaborback.domain.dto.clienteDto.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacadeImpl extends BaseFacadeImp<Cliente, ClienteDto,ClienteDto,Long> implements ClienteFacade {
    public ClienteFacadeImpl(BaseService<Cliente, Long> baseService, BaseMapper<Cliente, ClienteDto, ClienteDto> baseMapper) {
        super(baseService, baseMapper);
    }
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteService clienteService;

    @Override
    public ClienteShortDto obtenerClientePorUsuarioClienteId(Long id){
        return clienteMapper.toShortDto(clienteService.findClienteByUsuarioClienteId(id));
    }

    @Override
    public ClienteDto createCliente(ClienteCreateDto clienteCreate){
        return clienteMapper.toDTO(clienteService.createCliente(clienteMapper.toCreateEntity(clienteCreate)));

    }
}
