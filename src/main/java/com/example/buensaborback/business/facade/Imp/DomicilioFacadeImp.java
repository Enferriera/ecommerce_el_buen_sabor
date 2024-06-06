package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.DomicilioFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.DomicilioMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.domain.dto.DomicilioDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioFacadeImp extends BaseFacadeImp<Domicilio, DomicilioDto,DomicilioDto, Long> implements DomicilioFacade {
    public DomicilioFacadeImp(BaseService<Domicilio, Long> baseService, BaseMapper<Domicilio, DomicilioDto, DomicilioDto> baseMapper) {
        super(baseService, baseMapper);
    }
@Autowired
private DomicilioMapper domicilioMapper;

    @Autowired
    private DomicilioService domicilioService;

    @Override
    public List<DomicilioDto> findAllByClienteId(Long personaId) {
        return domicilioMapper.toDTOsList(domicilioService.findAllByClienteId(personaId));
    }
}
