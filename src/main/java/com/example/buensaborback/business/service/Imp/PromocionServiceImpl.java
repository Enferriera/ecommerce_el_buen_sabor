package com.example.buensaborback.business.service.Imp;

import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.business.service.PromocionService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.repositories.PromocionRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionServiceImpl extends BaseServiceImp<Promocion,Long> implements PromocionService {
    private final PromocionRepository promocionRepository;
    private final SucursalRepository sucursalRepository;

    public PromocionServiceImpl(PromocionRepository promocionRepository, SucursalRepository sucursalRepository) {
        super();
        this.promocionRepository = promocionRepository;
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public void changeHabilitado(Long id) {
        var promocion = getById(id);
        promocion.setHabilitado(!promocion.isHabilitado());
        baseRepository.save(promocion);
    }

    @Override
    public List<Promocion> findHabilitadasBySucursalId(Long sucursalId){
        return promocionRepository.findHabilitadasBySucursalId(sucursalId);
    }

    @Override
    public Promocion mapIdToPromocion(Long idPromocion){
        if (idPromocion == null) {
            return null;
        }
        return promocionRepository.getById(idPromocion);
    }

    @Override
    public Promocion getById(Long idPromocion){
        return promocionRepository.getById(idPromocion);
    }
}
