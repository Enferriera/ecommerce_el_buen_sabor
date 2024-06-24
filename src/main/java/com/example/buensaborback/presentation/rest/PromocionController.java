package com.example.buensaborback.presentation.rest;


import com.example.buensaborback.business.facade.PromocionFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/promociones")
@CrossOrigin(origins="*")
public class PromocionController {


    @Autowired
    private PromocionFacade promocionFacade;



    @GetMapping("/getHabilitadosPorSucursal/{idSucursal}")
    public ResponseEntity<?> getHabilitadosPorSucursal(@PathVariable Long idSucursal) {
        return ResponseEntity.ok().body(promocionFacade.findHabilitadasBySucursalId(idSucursal));
    }


}
