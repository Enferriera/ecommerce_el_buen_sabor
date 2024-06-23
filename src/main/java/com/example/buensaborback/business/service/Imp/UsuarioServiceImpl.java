package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.UsuarioService;
import com.example.buensaborback.domain.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseServiceImp<Usuario,Long> implements UsuarioService {
}
