package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio,Long>{
    @Query("SELECT d FROM Domicilio d JOIN d.clientes c WHERE c.id = :clienteId")
    List<Domicilio> findAllByClienteId(@Param("clienteId") Long clienteId);

    @Query("SELECT s.domicilio FROM Sucursal s WHERE s.id = :idSucursal")
    Domicilio findDomicilioBySucursalId(@Param("idSucursal") Long idSucursal);
}
