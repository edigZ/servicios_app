package com.mx.servicios.utils.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import com.mx.servicios.utils.log.Cronometro;
import com.mx.servicios.utils.log.LogCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ResultadoBD {

  @Autowired
  protected JdbcTemplate jdbcTemplate;

  public ResultadoBD() {

  }

  public ResultadoBD(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<? extends Object> ejecutaFuncionAll(String nombreFuncion, final List<Object> parametros,
                                                  final Class<?> clase) {
    FuncionesSistema funcionesSistema = FuncionesSistema.getInstance();
    List<Object> resultado = new ArrayList<>();
    final Funcion funcion = funcionesSistema.obtenerFuncion(nombreFuncion);
    String msgFuncion = "CALL " + funcion.getSchema() + "." + funcion.getNombreFuncion().split("\\(")[0] + "("
            + Arrays.toString(parametros.toArray()) + ")";
    Cronometro cronometro = new Cronometro(true, msgFuncion);

    try {
      resultado = jdbcTemplate.execute(con -> {
        CallableStatement callableStatement = con
                .prepareCall("{? = call " + funcion.getSchema() + "." + funcion.getNombreFuncion() + "}");
        return cargaParametros(callableStatement, parametros);
      }, (CallableStatementCallback<List<Object>>) cs -> {
        cs.execute();
        ResultSet resultSet = (ResultSet) cs.getObject(1);
        cronometro.stop();
        if (resultSet == null) {
          return null;
        }
        else {
          return obtenerresultadoAll(resultSet, funcion, clase);
        }
      });
    }
    catch (Exception ex) {
      ex.printStackTrace();
      LogCC.logDegug(ex);
      LogCC.log("Fallo en ejecutaFuncionAll");
    }

    return resultado;
  }

  public Object ejecutaFuncion(String nombreFuncion, final ArrayList<Object> parametros, final Class<?> clase) {
    FuncionesSistema funcionesSistema = FuncionesSistema.getInstance();
    Object resultado = new Object();
    final Funcion funcion = funcionesSistema.obtenerFuncion(nombreFuncion);
    String msgFuncion = "CALL " + funcion.getSchema() + "." + funcion.getNombreFuncion().split("\\(")[0] + "("
            + Arrays.toString(parametros.toArray()) + ")";
    Cronometro cronometro = new Cronometro(true, msgFuncion);
    try {
      resultado = jdbcTemplate.execute(con -> {
        CallableStatement callableStatement = con
                .prepareCall("{? = call " + funcion.getSchema() + "." + funcion.getNombreFuncion() + "}");
        return cargaParametros(callableStatement, parametros);

      }, (CallableStatementCallback<Object>) cs -> {

        cs.execute();
        ResultSet resultSet = (ResultSet) cs.getObject(1);
        cronometro.stop();
        return obtenerresultado(resultSet, funcion, clase);

      });

    }
    catch (Exception e) {
      LogCC.logDegug(e);
      LogCC.log("Fallo en ejecutaFuncion");
    }

    return resultado;
  }

  public CallableStatement cargaParametros(CallableStatement callableStatement,
                                           List<Object> parametros) {
    int posicion = 2;
    try {
      callableStatement.registerOutParameter(1, -10);
      for (Object parametro : parametros) {
        callableStatement.setObject(posicion, parametro);
        posicion++;
      }
    }
    catch (SQLException e) {
      LogCC.logDegug(e);
      LogCC.log("Ocurrio un problema en la Carga de Parametros.");
    }
    return callableStatement;
  }

  private List<Object> obtenerresultadoAll(ResultSet resultSet, Funcion funcion, Class<?> clase) {
    List<Object> resultado = new ArrayList<>();
    Map<String, Method> metodos;
    try {
      metodos = cargaMetodos(clase);
      while (resultSet.next()) {
        Object objeto = clase.newInstance();
        setValores(objeto, resultSet, funcion, metodos);
        resultado.add(objeto);
      }
    }
    catch (SQLException | InstantiationException | IllegalAccessException e) {
      LogCC.logDegug(e);
      LogCC.log("Ocurrio un problema al obtener el resulatdo.REF_1");
    }
    catch (IllegalArgumentException e) {
      LogCC.logDegug(e);
      LogCC.log("Ocurrio un problema al Ejecutar la Operacion.REF_4");
    }

    return resultado;
  }

  private Object obtenerresultado(ResultSet resultSet, Funcion funcion, Class<?> clase) {
    Object objeto = null;
    Map<String, Method> metodos = new HashMap<>();
    try {
      objeto = clase.newInstance();
      metodos = cargaMetodos(clase);
      while (resultSet.next()) {
        setValores(objeto, resultSet, funcion, metodos);
      }
    }
    catch (SQLException | InstantiationException | IllegalAccessException e) {
      LogCC.logDegug(e);
      LogCC.log("Ocurrio un problema al obtener el resulatdo.REF_1");
    }
    catch (IllegalArgumentException e) {
      LogCC.logDegug(e);
      LogCC.log("Ocurrio un problema al Ejecutar la Operacion.REF_4");
    }

    return objeto;
  }

  public Map<String, Method> cargaMetodos(Class<?> clase) {
    Map<String, Method> metodos = new HashMap<>();
    Method[] metodosClase = clase.getMethods();

    for (Method metodo : metodosClase) {
      if (metodo.getName().startsWith("set")) {
        Class<?>[] tipoDato = metodo.getParameterTypes();
        if (!tipoDato[0].getName().contains("int") && !tipoDato[0].getName().contains("double")) {
          metodos.put(metodo.getName().replace("set", "").toUpperCase(Locale.ROOT), metodo);
        }
      }
    }

    return metodos;
  }

  private void setValores(Object objeto, ResultSet resultSet, Funcion funcion, Map<String, Method> metodos) {
    funcion.getResultado().forEach((k, v) -> {
      try {
        Method metodo = metodos.get(k.toUpperCase(Locale.ROOT));
        Class<?>[] tipoDato = metodo.getParameterTypes();
        if (tipoDato[0].getName().contains("java.lang.String")) {
          String valor;
          valor = resultSet.getString(v);
          metodo.invoke(objeto, valor);
        }
        else if (tipoDato[0].getName().contains("java.lang.Number")) {
          String valorSRT = resultSet.getObject(String.valueOf(v)).toString().trim();
          Number valor = new BigDecimal(valorSRT);
          metodo.invoke(objeto, valor);
        }
        else if (tipoDato[0].getName().contains("long")) {
          String valorSRT = resultSet.getObject(String.valueOf(v)).toString().trim();
          metodo.invoke(objeto, Long.valueOf(valorSRT));
        }
        else if (tipoDato[0].getName().contains("java.util.Date")) {
          SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
          String valorSRT = resultSet.getObject(String.valueOf(v)).toString().trim();
          Date fecha = simpleDate.parse(valorSRT);
          metodo.invoke(objeto, fecha);
        }
      }
      catch (SQLException ex) {
        LogCC.logDegug(ex);
        LogCC.log("Ocurrio un problema setValores.REF_1");
      }
      catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
        LogCC.logDegug(e1);
        LogCC.log("Ocurrio un problema setValores.REF_2");
      }
      catch (ParseException e) {
        LogCC.logDegug(e);
        LogCC.log("Ocurrio un problema setValores.REF_3");
      }
      catch (Exception e2) {
        e2.printStackTrace();
        LogCC.logDegug(e2);
        LogCC.log("Ocurrio un problema setValores.REF_4");
      }
    });
  }
}
