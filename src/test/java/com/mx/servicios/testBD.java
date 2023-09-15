package com.mx.servicios;

import com.mx.servicios.utils.log.LogCC;
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
      String sql = "SELECT 1 FROM DUAL";
      try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        ResultSet resultSet = preparedStatement.executeQuery();
        assertTrue(resultSet.next());
        int resultValue = resultSet.getInt(1);
        LogCC.log("SALIDA + " + resultValue);
        assertTrue(resultValue == 1);
      }
      LogCC.log("Conexión exitosa a la base de datos.");
    }
    catch (SQLException e) {
      e.printStackTrace();
      // En caso de excepción, la conexión falló.
      throw new RuntimeException("No se pudo establecer la conexión a la base de datos.", e);
    }
  }
}
