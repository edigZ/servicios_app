package com.mx.servicios.dao;

import com.mx.servicios.beans.Ciudad;
import com.mx.servicios.utils.base.ResultadoBD;
import com.mx.servicios.utils.log.LogCC;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class GetCiudadesBase extends ResultadoBD {
  public GetCiudadesBase() {
  }

  public static final String FNGETCIUDADES = "GET_ALL_CIUDADES";

  public List<Ciudad> listaCiudad(int inicio, int fin) {
    List<Ciudad> campania = null;
    try {
      List<Object> parametros = Arrays.asList(inicio, fin);
      campania = (List<Ciudad>) ejecutaFuncionAll(FNGETCIUDADES, parametros, Ciudad.class);
    }
    catch (Exception e) {
      LogCC.log("fallo dao");
      e.printStackTrace();
    }
    return campania;
  }
}
