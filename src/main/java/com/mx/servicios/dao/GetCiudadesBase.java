package com.mx.servicios.dao;

import com.mx.servicios.beans.Ciudad;
import com.mx.servicios.utils.base.ResultadoBD;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GetCiudadesBase extends ResultadoBD {
  public GetCiudadesBase() {
  }

  public static final String FNGETCIUDADES = "GET_ALL_CIUDADES";

  public List<Ciudad> listaCiudad() {
    try {
      List<Ciudad> campania;
      ArrayList<Object> parametros = new ArrayList<>();
      parametros.add(1);
      parametros.add(100);
      campania = (List<Ciudad>) ejecutaFuncionAll(FNGETCIUDADES, parametros, Ciudad.class);
      return campania;
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
