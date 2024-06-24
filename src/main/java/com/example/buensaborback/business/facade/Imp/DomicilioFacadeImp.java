package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.DomicilioFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.DomicilioMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.ClienteService;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioFacadeImp extends BaseFacadeImp<Domicilio, DomicilioDto,DomicilioDto, Long> implements DomicilioFacade {
    @Autowired
    private ClienteRepository clienteRepository;

    public DomicilioFacadeImp(BaseService<Domicilio, Long> baseService, BaseMapper<Domicilio, DomicilioDto, DomicilioDto> baseMapper) {
        super(baseService, baseMapper);
    }
@Autowired
private DomicilioMapper domicilioMapper;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DomicilioService domicilioService;

    @Override
    public List<DomicilioDto> findAllByClienteId(Long personaId) {
        return domicilioMapper.toDTOsList(domicilioService.findAllByClienteId(personaId));
    }

    @Override
    public DomicilioDto findDomicilioBySucursalId(Long idSucursal){
        return domicilioMapper.toDTO(domicilioService.findDomicilioBySucursalId(idSucursal));
    }

    @Override
    @Transactional
    public DomicilioDto createDomicilio(DomicilioCreateDto domicilioCreateDto){
        Cliente cliente = clienteService.getById(domicilioCreateDto.getIdClientes().iterator().next());
        Domicilio domicilio = domicilioMapper.toCreateEntity(domicilioCreateDto);
        domicilioService.create(domicilio);
        cliente.getDomicilios().add(domicilio);
        clienteService.update(cliente, cliente.getId());

        return domicilioMapper.toDTO(domicilio);

    }
}
