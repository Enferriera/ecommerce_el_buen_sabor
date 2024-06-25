package com.example.buensaborback.business.service;

import com.example.buensaborback.business.service.Base.BaseService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Promocion;
import org.mapstruct.Named;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromocionService extends BaseService<Promocion,Long> {
    public void changeHabilitado(Long id);
    public List<Promocion> findHabilitadasBySucursalId(Long sucursalId);
    @Named("mapIdToPromocion")
    public Promocion mapIdToPromocion(Long idPromocion);
    @Override
    public Promocion getById(Long idPromocion);

}
