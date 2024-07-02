package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.enums.EstadoPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long>{

    @Query("SELECT p FROM Pedido p WHERE p.estadoPedido =:estado AND p.sucursal.id =:idSucursal")
    List<Pedido> findByEstadoPedidoAndSucursalId(@Param("estado")EstadoPedido estado, @Param("idSucursal")Long idSucursal);

    @Query("SELECT p FROM Pedido p JOIN p.cliente c JOIN c.usuarioCliente uc WHERE uc.id=:userId AND uc.email=:email  ORDER BY p.fechaPedido DESC")
    List<Pedido> findAllByUserId(@Param("userId")Long userId, @Param("email") String email);
}
