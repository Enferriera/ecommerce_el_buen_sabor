package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long>{

    @Query("SELECT d FROM Pedido p JOIN p.detallePedidos d WHERE p.id = :idPedido")
    List<DetallePedido> findAllByPedidoId(@Param("idPedido")Long idPedido);
}
