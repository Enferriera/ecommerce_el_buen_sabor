package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.UsuarioClienteFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.mapper.UsuarioClienteMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.business.service.UsuarioClienteService;
import com.example.buensaborback.domain.dto.usuarioClienteDto.UsuarioClienteDto;
import com.example.buensaborback.domain.entities.UsuarioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioClienteFacadeImpl extends BaseFacadeImp<UsuarioCliente, UsuarioClienteDto, UsuarioClienteDto, Long> implements UsuarioClienteFacade {
    @Autowired
    private UsuarioClienteService usuarioClienteService;
    @Autowired
    private UsuarioClienteMapper usuarioMapper;

    public UsuarioClienteFacadeImpl(BaseService<UsuarioCliente, Long> baseService, BaseMapper<UsuarioCliente, UsuarioClienteDto, UsuarioClienteDto> baseMapper) {
        super(baseService, baseMapper);
    }

    public UsuarioClienteDto create (UsuarioClienteDto usuarioDto){

        UsuarioCliente usuarioCliente = usuarioMapper.toEntity(usuarioDto);
        usuarioCliente.setClave(usuarioDto.getClaveEncriptada());
        usuarioCliente = usuarioClienteService.create(usuarioCliente);

        return usuarioMapper.toDTO(usuarioCliente);
    }
}
