package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  private static final String URL = "jdbc:postgresql://localhost:5432/carbon_emission";
  private static final String USER = "safwankherallah";
  private static final String PASSWORD = "123456";

  private static Connection connection = null;

  private ConnectionFactory() {}

  public static Connection getConnection(){
    if (connection == null){
      try{
        connection = DriverManager.getConnection(URL , USER, PASSWORD);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return  connection;
  }
}
