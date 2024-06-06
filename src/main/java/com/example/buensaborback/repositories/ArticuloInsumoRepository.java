package com.example.buensaborback.repositories;


import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo,Long> {
    List<ArticuloInsumo> findByEsParaElaborarTrue();
    List<ArticuloInsumo> findByEsParaElaborarFalse();
    @Query(value = "SELECT *\n" +
            "FROM ARTICULO_INSUMO ai\n" +
            "JOIN ARTICULO a ON ai.ID = a.ID\n" +
            "WHERE a.CATEGORIA_ID = ?1", nativeQuery = true)
    List<ArticuloInsumo> getArticulosByCategoria(Long idCategoria);

    Optional<ArticuloInsumo> findByCodigo(String codigo);

    List<ArticuloInsumo> findByHabilitadoTrueAndEsParaElaborarFalseAndCategoriaDenominacion(String categoria);



    @Query("SELECT a FROM Articulo a " +
            "JOIN a.categoria c " +
            "JOIN c.sucursales s " +
            "WHERE a.habilitado = true " +
            "AND a.eliminado = false " +
            "AND s.id = :sucursalId " +
            "AND c.denominacion = :categoria")
    List<ArticuloInsumo> findByHabilitadoNoElaboradosPorIdSucursalYCategoria(@Param("sucursalId") Long sucursalId, @Param("categoria")String categoria);

    Optional<ArticuloInsumo> findById(Long id);

}
