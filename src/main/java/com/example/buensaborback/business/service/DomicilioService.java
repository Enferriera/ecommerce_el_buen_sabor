package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomicilioService extends BaseService<Domicilio,Long> {
    public List<Domicilio> findAllByClienteId(Long personaId);

    Domicilio findDomicilioBySucursalId(Long idSucursal);

}
