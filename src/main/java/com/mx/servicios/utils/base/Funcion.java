package com.mx.servicios.utils.base;

import java.util.Map;

public class Funcion {

  private String schema;
  private String nombreFuncion;
  private String llaveFuncion;
  private Map<String, String> resultado;

  /**
   * Constructor de la clse
   */
  public Funcion() {
    /**
     * Constructor vacio
     * no hace nada
     */
    super();
  }

  /**
   * Metodo get
   *
   * @return
   */
  public String getSchema() {
    return schema;
  }

  /**
   * Metodo set
   *
   * @param schema
   */
  public void setSchema(String schema) {
    this.schema = schema;
  }

  /**
   * Metodo get
   *
   * @return
   */
  public String getNombreFuncion() {
    return nombreFuncion;
  }

  /**
   * Metodo set
   *
   * @param nombreFuncion
   */
  public void setNombreFuncion(String nombreFuncion) {
    this.nombreFuncion = nombreFuncion;
  }

  /**
   * Metodo get
   *
   * @return
   */
  public String getLlaveFuncion() {
    return llaveFuncion;
  }

  /**
   * Metodo set
   *
   * @param llaveFuncion
   */
  public void setLlaveFuncion(String llaveFuncion) {
    this.llaveFuncion = llaveFuncion;
  }

  /**
   * Metodo get
   */
  public Map<String, String> getResultado() {
    return resultado;
  }

  /**
   * Metodo set
   *
   * @param resultado
   */
  public void setResultado(Map<String, String> resultado) {
    this.resultado = resultado;
  }
}
