package com.mx.servicios.utils.base;

import com.mx.servicios.utils.log.LogCC;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncionesSistema {
  private static FuncionesSistema funcionesSistema;
  private Map<String, Funcion> mapFuncionesSistema;

  private FuncionesSistema() {
    mapFuncionesSistema = new HashMap<>();
  }

  public static FuncionesSistema getInstance() {
    if (funcionesSistema == null) {
      funcionesSistema = new FuncionesSistema();
      funcionesSistema.cargaFunciones();
    }
    return funcionesSistema;
  }

  private void cargaFunciones() {
    Yaml yaml = new Yaml();
    try {
      LogCC.log("Cargando Funciones del Sistema.");

      List<InputStream> recursos = new ArrayList<>();
      recursos.add(Thread.currentThread().getContextClassLoader().getResourceAsStream("Utilerias.yml"));

      for (InputStream recurso : recursos) {
        YamlUtils yamlUtils = yaml.loadAs(recurso, YamlUtils.class);
        for (Funcion funcion : yamlUtils.getFunciones()) {
          mapFuncionesSistema.put(funcion.getLlaveFuncion().trim(), funcion);
        }

        recurso.close();
      }

      LogCC.log("Funciones del Sistema Cargadas Exitosamente.");
    }
    catch (IOException e) {
      LogCC.logDegug(e);
      LogCC.log("Ocurrio un detalle al Cargar la Funciones del Sistema.");
    }
  }

  public Funcion obtenerFuncion(String clave) {
    return mapFuncionesSistema.get(clave);
  }
}
