package com.mx.servicios.controllers;


import com.mx.servicios.beans.Ciudad;
import com.mx.servicios.dao.GetCiudadesBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que realiza las operaciones
 * para la tabla de ciudades
 */
@CrossOrigin
@RestController
@RequestMapping("/v1")
@Tag(name = "Ciudades", description = "Contiene los metodos para la informacion de ciudadez")
public class CiudadesController {
  private final GetCiudadesBase getCiudadesBase;

  @Autowired
  public CiudadesController(GetCiudadesBase getCiudadesBase) {
    this.getCiudadesBase = getCiudadesBase;
  }

  @Operation(summary = "Obtiene todas las ciudades desde la base")
  @GetMapping(value = "/all-ciudades")
  public List<Ciudad> obtenerDatosDePrueba() {
    try {
      return getCiudadesBase.listaCiudad();
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}