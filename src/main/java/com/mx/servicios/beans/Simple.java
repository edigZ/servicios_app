package com.mx.servicios.beans;


public class Simple {
  private String id;
  private String descripcion;

  public Simple() {
  }

  public Simple(String id, String descripcion) {
    this.id = id;
    this.descripcion = descripcion;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
    sb.append("id='").append(id).append('\'');
    sb.append(", descripcion='").append(descripcion).append('\'');
    return sb.toString();
  }
}
