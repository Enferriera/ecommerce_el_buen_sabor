package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long>{
    List<Categoria> findByEsInsumoTrue();
    List<Categoria> findByEsInsumoFalse();
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.sucursales WHERE c.id = :id")
    Categoria findWithSucursalesById(@Param("id") Long id);
    @Query(value = "SELECT c.ID, c.ELIMINADO, c.DENOMINACION, c.CATEGORIA_ID\n" +
            "FROM CATEGORIA c\n" +
            "JOIN SUCURSAL_CATEGORIA sc ON c.ID = sc.CATEGORIA_ID\n" +
            "JOIN SUCURSAL s ON sc.SUCURSAL_ID = s.ID\n" +
            "WHERE s.ID = ?1", nativeQuery = true)
    List<Categoria> getCategoriasBySucursal(Long idSucursal);

    @Query("SELECT DISTINCT c FROM Categoria c " +
            "JOIN c.sucursales s " +
            "JOIN c.articulos a " +
            "LEFT JOIN ArticuloInsumo ai ON a.id = ai.id " +
            "LEFT JOIN ArticuloManufacturado am ON a.id = am.id " +
            "WHERE s.id = :sucursalId " +
            "AND ((am.habilitado = true AND am.eliminado=false) OR (ai.habilitado = true AND ai.eliminado =false AND ai.esParaElaborar = false OR ai.esParaElaborar IS NULL))")
    List<Categoria> findCategoriasBySucursalAndArticuloType(@Param("sucursalId") Long sucursalId);
}
