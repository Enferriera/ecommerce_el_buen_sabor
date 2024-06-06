package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.DomicilioService;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioServiceImp extends BaseServiceImp<Domicilio,Long> implements DomicilioService {
@Autowired
private DomicilioRepository repository;
    @Override
    public List<Domicilio> findAllByClienteId(Long personaId) {
        return this.repository.findAllByClienteId(personaId);
    }
}
