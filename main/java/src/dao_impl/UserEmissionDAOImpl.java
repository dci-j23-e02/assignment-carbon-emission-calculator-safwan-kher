package dao_impl;

import dao_interfaces.UserEmissionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_classes.UserEmission;
import queries_enums.CRUDQueries.UserEmissionCRUDQueries;
import util.ConnectionFactory;


public class UserEmissionDAOImpl implements UserEmissionDAO {
  private Connection connection = ConnectionFactory.getConnection();

  @Override
  public void insertUserEmission(UserEmission userEmission) {
    String sql = UserEmissionCRUDQueries.INSERT_USER_EMISSION.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, userEmission.getUserId());
      pstmt.setLong(2, userEmission.getActivityId());
      pstmt.setDouble(3, userEmission.getQuantity());
      pstmt.setDouble(4, userEmission.getEmission());
      pstmt.setDate(5, java.sql.Date.valueOf(userEmission.getDate()));
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public UserEmission getUserEmission(Long emissionId) {
    String sql = UserEmissionCRUDQueries.GET_USER_EMISSION.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, emissionId);
      ResultSet resultSet = pstmt.executeQuery();
      if (resultSet.next()) {
        return extractUserEmissionFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void updateUserEmission(UserEmission userEmission) {
    String sql = UserEmissionCRUDQueries.UPDATE_USER_EMISSION.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, userEmission.getUserId());
      pstmt.setLong(2, userEmission.getActivityId());
      pstmt.setDouble(3, userEmission.getQuantity());
      pstmt.setDouble(4, userEmission.getEmission());
      pstmt.setDate(5, java.sql.Date.valueOf(userEmission.getDate()));
      pstmt.setLong(6, userEmission.getEmissionId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteUserEmission(Long emissionId) {
    String sql = UserEmissionCRUDQueries.DELETE_USER_EMISSION.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, emissionId);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private UserEmission extractUserEmissionFromResultSet(ResultSet resultSet) throws SQLException {
    UserEmission userEmission = new UserEmission();
    userEmission.setEmissionId(resultSet.getLong("emission_id"));
    userEmission.setUserId(resultSet.getLong("user_id"));
    userEmission.setActivityId(resultSet.getLong("activity_id"));
    userEmission.setQuantity(resultSet.getDouble("quantity"));
    userEmission.setEmission(resultSet.getDouble("emission"));
    userEmission.setDate(resultSet.getDate("date").toLocalDate());
    return userEmission;
  }
}
