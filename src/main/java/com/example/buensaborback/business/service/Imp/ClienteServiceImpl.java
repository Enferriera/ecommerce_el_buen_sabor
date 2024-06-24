package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.domain.dto.clienteDto.ClienteCreateDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends BaseServiceImp<Cliente,Long> implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        super();
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente findClienteByUsuarioClienteId(Long id){
        return clienteRepository.findClienteByUsuarioClienteId(id);
    }

    @Override
    @Transactional
    public Cliente createCliente(Cliente cliente){

        return clienteRepository.save(cliente);
    }
}
