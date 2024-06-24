package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioCreateDto;
import com.example.buensaborback.domain.dto.domicilioDto.DomicilioDto;

import java.util.List;

public interface DomicilioFacade extends BaseFacade<DomicilioDto, DomicilioDto, Long> {
    public List<DomicilioDto> findAllByClienteId(Long personaId);

    public DomicilioDto findDomicilioBySucursalId(Long idSucursal);

    public DomicilioDto createDomicilio(DomicilioCreateDto domicilioCreateDto);
}
