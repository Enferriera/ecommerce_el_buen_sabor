package com.example.buensaborback.business.facade.Imp;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.business.facade.UsuarioFacade;
import com.example.buensaborback.business.mapper.BaseMapper;
import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.dto.UsuarioDto;
import com.example.buensaborback.domain.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioFacadeImpl extends BaseFacadeImp<Usuario,UsuarioDto, UsuarioDto,Long> implements UsuarioFacade {
    public UsuarioFacadeImpl(BaseService<Usuario, Long> baseService, BaseMapper<Usuario, UsuarioDto, UsuarioDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
