package com.mx.servicios.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class ResponseBase {
  private String codigo;
  private String mensaje;
  private String folio;
  @JsonIgnore
  private HttpStatus httpStatus;

  /**
   * Constructor vacio
   */
  public ResponseBase() {
    /**
     * Constructor vacio
     */
  }

  /**
   * Metodo para evitar mas returns
   *
   * @param codigo
   */
  public ResponseBase(CodigoResponse codigo, String folio) {
    this.codigo = codigo.getCodigo();
    this.mensaje = codigo.getDescripcion();
    this.folio = folio;
  }

  /**
   * @param respuesta
   */
  public void setResponseCode(CodigoResponse respuesta) {
    this.codigo = respuesta.getCodigo();
    this.mensaje = respuesta.getDescripcion();
    this.httpStatus = respuesta.getHttpStatus();
  }

  /**
   * @return
   */
  public String getCodigo() {
    return this.codigo;
  }

  /**
   * @return
   */
  public String getMensaje() {
    return this.mensaje;
  }

  /**
   * @return
   */
  public String getFolio() {
    return this.folio;
  }

  /**
   * @param codigo
   */
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  /**
   * @param mensaje
   */
  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  /**
   * @param folio
   */
  public void setFolio(String folio) {
    this.folio = folio;
  }

  /**
   * @return
   */
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  /**
   * @param httpStatus
   */
  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }
}
