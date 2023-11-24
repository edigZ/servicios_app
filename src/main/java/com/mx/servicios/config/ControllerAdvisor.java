package com.mx.servicios.config;

import com.mx.servicios.utils.response.CodigoResponse;

import com.mx.servicios.utils.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ResponseBase> handleAllExceptions(Throwable ex) {

    var respuesta = new ResponseBase();

    List<Object> listDetalles = new ArrayList<>();
    listDetalles.add(ex.getMessage());

    respuesta.setResponseCode(CodigoResponse.CODIGO_400);
    respuesta.setResultado(listDetalles);

    return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
  }
}
