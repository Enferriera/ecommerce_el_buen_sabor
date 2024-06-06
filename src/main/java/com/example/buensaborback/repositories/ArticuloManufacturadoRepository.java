package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {
    @Query(value = "SELECT EXISTS(\n" +
            "    SELECT 1\n" +
            "    FROM PROMOCION_DETALLE pd\n" +
            "    WHERE pd.ARTICULO_ID = ?1 AND ELIMINADO = FALSE\n" +
            ");", nativeQuery = true)
    boolean contiene(Long id);

    @Query(value = "SELECT *\n" +
            "FROM ARTICULO_MANUFACTURADO am\n" +
            "JOIN ARTICULO a ON am.ID = a.ID\n" +
            "WHERE a.CATEGORIA_ID = ?1", nativeQuery = true)
    List<ArticuloManufacturado> getArticulosByCategoria(Long id);


    @Query("SELECT a FROM ArticuloManufacturado a " +
            "JOIN a.categoria c " +
            "JOIN c.sucursales s " +
            "WHERE a.habilitado = true AND s.id = :sucursalId")
    List<ArticuloManufacturado> findHabilitadosBySucursal(@Param("sucursalId") Long sucursalId);
  
    Optional<ArticuloManufacturado> findByCodigo(String codigo);

    List<ArticuloManufacturado> findByHabilitadoTrueAndCategoriaDenominacion(String categoria);


    @Query("SELECT a FROM ArticuloManufacturado a " +

            "JOIN a.categoria c " +
            "JOIN c.sucursales s " +
            "WHERE a.habilitado = true AND a.eliminado=false AND s.id = :sucursalId AND c.denominacion = :categoriaNombre")
    List<ArticuloManufacturado> findHabilitadosBySucursalAndCategoria(@Param("sucursalId") Long sucursalId, @Param("categoriaNombre") String categoriaNombre);


}
