package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteDto;

public interface UsuarioClienteFacade extends BaseFacade<UsuarioClienteDto, UsuarioClienteDto, Long> {

    public UsuarioClienteDto create(UsuarioClienteDto usuarioDto);
}
