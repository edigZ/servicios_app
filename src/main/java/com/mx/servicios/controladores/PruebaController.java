package com.mx.servicios.controladores;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/actualizacion-datos")
@Tag(name = "PruebaController", description = "contiene controladores de prueba dos partes")
public class PruebaController {

    @Operation(summary = "Se genera un propuesta de actualizacion de domicilio")
    @GetMapping(value = "/obtener-datos", headers = MediaType.APPLICATION_JSON_VALUE)
    public String obtenerDatosDePrueba() {
        return "Datos de prueba";
    }

    @Operation(summary = "Se genera un propuesta de actualizacion de domicilio")
    @PostMapping(value = "/obtener-datos", headers = MediaType.APPLICATION_JSON_VALUE)
    public String obtenerDatosDePrueba1() {
        return "Datos de prueba";
    }

    @Operation(summary = "Se genera un propuesta de actualizacion de domicilio")
    @PutMapping(value = "/obtener-datos", headers = MediaType.APPLICATION_JSON_VALUE)
    public String obtenerDatosDePrueba2() {
        return "Datos de prueba";
    }

    @Operation(summary = "Se genera un propuesta de actualizacion de domicilio")
    @DeleteMapping(value = "/obtener-datos", headers = MediaType.APPLICATION_JSON_VALUE)
    public String obtenerDatosDePrueba3() {
        return "Datos de prueba";
    }

}