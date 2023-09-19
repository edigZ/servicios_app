package com.mx.servicios.utils.response;

import org.springframework.http.HttpStatus;

public enum CodigoResponse {
  /**
   * The codigo 200.
   */
  CODIGO_200("200.App-servicios",
          "Operación exitosa.",
          HttpStatus.OK),

  /**
   * The codigo 201.
   */
  CODIGO_201("201.App-servicios",
          "Operación exitosa.",
          HttpStatus.CREATED),

  /**
   * The codigo 400.
   */
  CODIGO_400("400.App-servicios",
          "Datos de entrada incorrectos, favor de validar.",
          HttpStatus.BAD_REQUEST),

  /**
   * The codigo 404.
   */
  CODIGO_404("404.App-servicios",
          "Recurso no encontrado.",
          HttpStatus.NOT_FOUND),

  /**
   * The codigo 405.
   */
  CODIGO_405("404.App-servicios",
          "Método no Soportado.",
          HttpStatus.METHOD_NOT_ALLOWED),

  /**
   * The codigo 500.
   */
  CODIGO_500("500.App-servicios",
          "Fallo en el servidor al procesar la petición.",
          HttpStatus.INTERNAL_SERVER_ERROR);

  /**
   * The codigo.
   */
  private String codigo;

  /**
   * The descripcion.
   */
  private String descripcion;

  /**
   * The http status.
   */
  private HttpStatus httpStatus;

  /**
   * Instantiates a new codigo response.
   *
   * @param codigo      the codigo
   * @param descripcion the descripcion
   * @param httpStatus  the http status
   */
  private CodigoResponse(String codigo, String descripcion, HttpStatus httpStatus) {
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.httpStatus = httpStatus;
  }

  /**
   * Gets the codigo.
   *
   * @return the codigo
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Gets the descripcion.
   *
   * @return the descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Gets the http status.
   *
   * @return the http status
   */
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
