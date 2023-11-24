package com.mx.servicios.controllers;

import com.mx.servicios.beans.Ciudad;
import com.mx.servicios.beans.Simple;
import com.mx.servicios.dao.CiudadesDao;
import com.mx.servicios.utils.log.LogCC;
import com.mx.servicios.utils.response.CodigoResponse;
import com.mx.servicios.utils.response.Folio;
import com.mx.servicios.utils.response.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

/**
 * Controlador que realiza las operaciones
 * para la tabla de ciudades
 */
@CrossOrigin
@RestController
@RequestMapping("/v1")
@Tag(name = "Ciudades", description = "Contiene los metodos para la informacion de ciudades")
public class CiudadesController {
  private final CiudadesDao ciudadesDao;

  @Autowired
  public CiudadesController(CiudadesDao ciudadesDao) {
    this.ciudadesDao = ciudadesDao;
  }

  /**
   * @param inicio valor de la lista
   * @return lista de ciudades
   */
  @Operation(summary = "Obtiene todas las ciudades desde la base")
  @GetMapping(value = "/all-ciudades")
  @ResponseBody
  public Object obtenerAllCiudad(@RequestParam(name = "inicio") Integer inicio) {
    /* declaracion de variables */
    List<Ciudad> ciudadList;
    ResponseBase respuesta = respuesta();
    try {
      /* realiza consulta a la base */
      int fin = -999;
      ciudadList = ciudadesDao.listaCiudad(inicio, fin);

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

  /**
   * @param idCiudad id
   * @param nombre   nombre
   * @return resultado de la inserccion desde la funcion
   */
  @Operation(summary = "inserta una ciudad a la base")
  @PostMapping(value = "/insert-ciudades")
  @ResponseBody
  public Object insertCiudad(@RequestParam(name = "idCiudad") String idCiudad,
                             @RequestParam(name = "nombreCD") String nombre) {
    Simple salida;
    ResponseBase respuesta = respuesta();
    try {
      /*validacion de datos de entrada*/
      if (idCiudad.isEmpty() || nombre.isEmpty()) {
        respuesta.setResponseCode(CodigoResponse.CODIGO_400);
        respuesta.setResultado("Valide su infomacion");
      }
      else {
        salida = ciudadesDao.insertaCiudad(idCiudad, nombre);
        LogCC.log(salida);
        if (salida.getId().equals("2")) {
          respuesta.setResponseCode(CodigoResponse.CODIGO_400);
          respuesta.setResultado(salida);
        }
        else {
          respuesta.setResponseCode(CodigoResponse.CODIGO_201);
          respuesta.setResultado(salida);
        }
      }
    }
    catch (Exception e) {
      respuesta.setResponseCode(CodigoResponse.CODIGO_500);
      respuesta.setResultado(List.of("Fallo inserta Ciudades"));
    }
    return new ResponseEntity<>(respuesta, respuesta.getHttpStatus());
  }

  /**
   * Metodo para no duplicar codigo
   *
   * @return response
   */
  private ResponseBase respuesta() {
    ResponseBase salida = new ResponseBase();
    salida.setFolio(Folio.getFolio());
    return salida;
  }
}