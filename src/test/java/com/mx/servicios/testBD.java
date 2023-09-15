package com.mx.servicios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class testBD {


  private DataSource dataSource;

  @Autowired
  public testBD(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Test
  public void testDatabaseConnection() {
    try (Connection connection = dataSource.getConnection()) {
      // Si llegamos aquí sin lanzar una excepción, la conexión fue exitosa.
      String sql = "SELECT 1 FROM DUAL";
      try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        ResultSet resultSet = preparedStatement.executeQuery();

        // Verificar si la consulta se ejecutó correctamente y si devuelve resultados
        assertTrue(resultSet.next()); // assertTrue verifica que haya al menos una fila en el resultado
        int resultValue = resultSet.getInt(1); // Obtenemos el valor de la primera columna
        System.out.println("SALIDA + " + resultValue);
        assertTrue(resultValue == 1); // Verificamos que el valor sea igual a 1
      }
      System.out.println("Conexión exitosa a la base de datos.");
    }
    catch (SQLException e) {
      e.printStackTrace();
      // En caso de excepción, la conexión falló.
      throw new RuntimeException("No se pudo establecer la conexión a la base de datos.", e);
    }
  }
}
