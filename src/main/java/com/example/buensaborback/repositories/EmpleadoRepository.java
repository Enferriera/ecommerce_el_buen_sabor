package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.enums.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado, Long>{
    @Query("SELECT COUNT(e) FROM Empleado e " +
            "JOIN e.usuario u " +
            "WHERE e.sucursal.id = :sucursalId " +
            "AND u.rol = :rol")
    int countEmpleadosBySucursalAndRol(@Param("sucursalId") Long sucursalId, @Param("rol") Rol rol);
}
