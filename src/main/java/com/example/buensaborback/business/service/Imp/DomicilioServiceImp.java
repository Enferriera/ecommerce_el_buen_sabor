package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.repositories.DomicilioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioServiceImp extends BaseServiceImp<Domicilio,Long> implements DomicilioService {
@Autowired
private DomicilioRepository domicilioRepository;
    @Override
    @Transactional
    public List<Domicilio> findAllByClienteId(Long personaId) {
        return domicilioRepository.findAllByClienteId(personaId);
    }

    @Override
    @Transactional
    public   Domicilio findDomicilioBySucursalId(Long idSucursal){
        return domicilioRepository.findDomicilioBySucursalId(idSucursal);
    }

}
