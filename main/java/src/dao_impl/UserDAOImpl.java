package dao_impl;

import dao_interfaces.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_classes.User;
import queries_enums.CRUDQueries.UserCRUDQueries;
import util.ConnectionFactory;


public class UserDAOImpl implements UserDAO {
  // Attribute
  private Connection connection = ConnectionFactory.getConnection();

  @Override
  public void insertUser(User user) {
String sql = UserCRUDQueries.INSERT_USER.getQuery();
try(
    PreparedStatement pstmt = connection.prepareStatement(sql)
    ){
  pstmt.setString(1, user.getUserName());
  pstmt.setString(2, user.getEmail());
  pstmt.setString(3, user.getPasswordHash());
  pstmt.executeUpdate();
}catch(SQLException e){
  e.printStackTrace();
}
  }

  @Override
  public User getUser(Long userId) {
    String sql = UserCRUDQueries.GET_USER.getQuery();
    try(
        PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
      pstmt.setLong(1, userId);
      ResultSet resultSet = pstmt.executeQuery();
      if(resultSet.next()){
        return  extractUserFromResultSet(resultSet);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return null;
  }



  @Override
  public void updateUser(User user) {
    Connection connection = ConnectionFactory.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(UserCRUDQueries.UPDATE_USER.getQuery())) {
      ps.setString(1, user.getUserName());
      ps.setString(2, user.getEmail());
      ps.setString(3, user.getPasswordHash()); // Ideally, hash the password
      ps.setLong(4, user.getUserId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteUser(Long userId) {
    Connection connection = ConnectionFactory.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(UserCRUDQueries.DELETE_USER.getQuery())) {
      ps.setLong(1, userId);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
    User user = new User();
    user.setUserId(resultSet.getLong("user_id"));
    user.setUserName(resultSet.getString("username"));
    user.setEmail(resultSet.getString("email"));
    user.setPasswordHash(resultSet.getString("password_hash"));

    return user;

  }
}
