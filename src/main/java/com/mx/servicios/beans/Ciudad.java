package com.mx.servicios.beans;

public class Ciudad {
  String idCiudad;
  String descripcion;

  public Ciudad() {
  }

  public String getIdCiudad() {
    return idCiudad;
  }

  public void setIdCiudad(String idCiudad) {
    this.idCiudad = idCiudad;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("idCiudad='").append(idCiudad).append('\'');
    sb.append(", descripcion='").append(descripcion).append('\'');
    return sb.toString();
  }
}
