package com.mx.servicios.dao;

import com.mx.servicios.beans.Ciudad;
import com.mx.servicios.beans.Simple;
import com.mx.servicios.utils.base.ResultadoBD;
import com.mx.servicios.utils.log.LogCC;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CiudadesDao extends ResultadoBD {

  private static final String FN_GETCIUDADES = "GET_ALL_CIUDADES";
  private static final String FN_INSERTCIUDAD = "SET_CIUDADES";


  public CiudadesDao(JdbcTemplate jdbcTemplate) {
    super(jdbcTemplate);
  }

  /**
   * @param inicio inicio de la lista
   * @param fin    numero maximo de la lista
   * @return una lista con todas las ciudades
   */
  public List<Ciudad> listaCiudad(int inicio, int fin) {
    List<Ciudad> resultado = null;
    try {
      List<Object> parametros = Arrays.asList(inicio, fin);
      resultado = (List<Ciudad>) ejecutaFuncionAll(FN_GETCIUDADES, parametros, Ciudad.class);
    }
    catch (Exception e) {
      e.printStackTrace();
      LogCC.log("fallo dao listar ciudad");
      LogCC.log(e.getMessage());
    }
    return resultado;
  }


  /**
   * @param ciudad nombre
   * @param nombre nombre-ciudad
   */
  public Simple insertaCiudad(String ciudad, String nombre) {
    Simple salida = null;
    try {
      List<Object> parametros = Arrays.asList(ciudad, nombre);
      salida = (Simple) ejecutaFuncion(FN_INSERTCIUDAD, parametros, Simple.class);
    }
    catch (Exception e) {
      LogCC.log("Fallo dao insertar ciudad");
      LogCC.log(e.getMessage());
    }
    return salida;
  }
}
