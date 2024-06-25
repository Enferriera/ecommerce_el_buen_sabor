package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.promocionDto.PromocionDto;
import com.example.buensaborback.domain.dto.promocionDto.PromocionShortDto;
import com.example.buensaborback.domain.entities.Promocion;

import java.util.List;

public interface PromocionFacade extends BaseFacade<PromocionDto,PromocionDto, Long> {



    public void changeHabilitado(Long id);

    public List<PromocionDto> findHabilitadasBySucursalId(Long sucursalId);


    public PromocionShortDto obtenerPorId(Long idPromocion);

}
