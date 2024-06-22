package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;
import org.springframework.data.repository.query.Param;

public interface EmpleadoService extends BaseService<Empleado,Long> {
    int countEmpleadosBySucursalAndRol(Long sucursalId, Rol rol);


}
