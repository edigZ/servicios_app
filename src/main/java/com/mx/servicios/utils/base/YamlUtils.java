package com.mx.servicios.utils.base;

import java.util.List;

public class YamlUtils {

  private List<Funcion> funciones;

  /**
   * Constructor
   */
  public YamlUtils() {
    /**
     * constuctor super();
     */
  }

  /**
   * Metodo get
   *
   * @return
   */
  public List<Funcion> getFunciones() {
    return funciones;
  }

  /**
   * Metodo set
   *
   * @param funciones
   */
  public void setFunciones(List<Funcion> funciones) {
    this.funciones = funciones;
  }
}
