package com.mx.servicios.beans;

public class Simple {
  private int id;
  private String descripcion;

  public Simple() {
  }

  public Simple(int id, String descripcion) {
    this.id = id;
    this.descripcion = descripcion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
