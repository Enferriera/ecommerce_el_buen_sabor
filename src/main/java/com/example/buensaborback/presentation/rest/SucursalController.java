package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.Imp.SucursalFacadeImp;
import com.example.buensaborback.business.facade.SucursalFacade;
import com.example.buensaborback.business.service.Base.BaseServiceImp;
import com.example.buensaborback.domain.dto.CategoriaDtos.CategoriaGetDto;
import com.example.buensaborback.domain.dto.SucursalDtos.SucursalDto;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
@CrossOrigin(origins="*")
public class SucursalController {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImp.class);
   @Autowired
   private SucursalFacade facade;



    @GetMapping("/categorias/{id}")
    public List<CategoriaGetDto> getCategoriasBySucursalId(@PathVariable Long id) {
        return facade.findCategoriasBySucursalId(id);
    }

    @GetMapping("/porEmpresa/{id}")
    public List<SucursalDto> getSucursalesByEmpresaId(@PathVariable Long id) {
        return facade.findAllByEmpresaId(id);
    }
}
