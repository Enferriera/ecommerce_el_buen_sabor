package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.UsuarioClienteService;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import com.example.buensaborback.repositories.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioClienteServiceImpl extends BaseServiceImp<UsuarioCliente, Long> implements UsuarioClienteService {
    @Autowired
    UsuarioClienteRepository repository;
    @Override
    public boolean existsByEmail(String email) {
        System.out.println("checkeando el mail "+email);
        return repository.existsByEmail(email);
    }
}
