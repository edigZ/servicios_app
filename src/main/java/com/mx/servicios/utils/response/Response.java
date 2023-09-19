package com.mx.servicios.utils.response;

public class Response extends ResponseBase {

  private Object resultado;

  /**
   * @param codigo
   * @param folio
   * @param resulObject
   */
  public Response(CodigoResponse codigo, String folio, Object resulObject) {
    super(codigo, folio);
    this.resultado = resulObject;
  }

  /**
   * constructor vacio
   */
  public Response() {
  }

  /**
   *
   */
  public Object getResultado() {
    return resultado;
  }

  /**
   *
   */
  public void setResultado(Object resultado) {
    this.resultado = resultado;
  }
}
