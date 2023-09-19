package com.mx.servicios.beans;

import java.util.Map;

public class Funcion {
  private String schema;
  private String nombreFuncion;
  private String llaveFuncion;
  private Map<String, String> resultado;

  public Funcion() {
    super();
  }

  public String getSchema() {
    return schema;
  }

  public void setSchema(String schema) {
    this.schema = schema;
  }

  public String getNombreFuncion() {
    return nombreFuncion;
  }

  public void setNombreFuncion(String nombreFuncion) {
    this.nombreFuncion = nombreFuncion;
  }

  public String getLlaveFuncion() {
    return llaveFuncion;
  }

  public void setLlaveFuncion(String llaveFuncion) {
    this.llaveFuncion = llaveFuncion;
  }

  public Map<String, String> getResultado() {
    return resultado;
  }

  public void setResultado(Map<String, String> resultado) {
    this.resultado = resultado;
  }
}
