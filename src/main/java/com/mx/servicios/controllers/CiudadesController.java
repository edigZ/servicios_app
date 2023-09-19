package com.mx.servicios.controllers;


import com.mx.servicios.beans.Ciudad;
import com.mx.servicios.dao.GetCiudadesBase;
import com.mx.servicios.utils.response.CodigoResponse;
import com.mx.servicios.utils.response.Folio;
import com.mx.servicios.utils.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
  @ResponseBody
  public Object obtenerDatosDePrueba(@RequestParam(name = "inicio", required = true) Integer inicio) {
    /* declaracion de variables */
    List<Ciudad> ciudadList;
    Response respuesta = respuesta();
    try {
      /* realiza consulta a la base */
      int fin = -999;
      ciudadList = getCiudadesBase.listaCiudad(inicio, fin);

      if (ciudadList == null || ciudadList.isEmpty()) {
        respuesta.setResponseCode(CodigoResponse.CODIGO_404);
        respuesta.setResultado(List.of("No se encontro informacion"));
      }
      else {
        respuesta.setResponseCode(CodigoResponse.CODIGO_200);
        respuesta.setResultado(ciudadList);
      }
    }
    catch (Exception e) {
      respuesta.setResponseCode(CodigoResponse.CODIGO_500);
      respuesta.setResultado(List.of("Fallo consulta Ciudades"));
      e.printStackTrace();
    }
    return new ResponseEntity<>(respuesta, respuesta.getHttpStatus());
  }

  private Response respuesta() {
    Response salida = new Response();
    salida.setFolio(Folio.getFolio());
    return salida;
  }
}