package dao_impl;

import dao_interfaces.EmissionGoalDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_classes.EmissionGoal;
import queries_enums.CRUDQueries.EmissionGoalCRUDQueries;
import util.ConnectionFactory;


public class EmissionGoalDAOImpl implements EmissionGoalDAO {
  private Connection connection = ConnectionFactory.getConnection();

  @Override
  public void insertEmissionGoal(EmissionGoal emissionGoal) {
    String sql = EmissionGoalCRUDQueries.INSERT_GOAL.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, emissionGoal.getUserId());
      pstmt.setDouble(2, emissionGoal.getTargetEmission());
      pstmt.setDate(3, java.sql.Date.valueOf(emissionGoal.getStartDate()));
      pstmt.setDate(4, java.sql.Date.valueOf(emissionGoal.getEndDate()));
      pstmt.setString(5, emissionGoal.getStatus());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public EmissionGoal getEmissionGoal(Long goalId) {
    String sql = EmissionGoalCRUDQueries.GET_GOAL.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, goalId);
      ResultSet resultSet = pstmt.executeQuery();
      if (resultSet.next()) {
        return extractEmissionGoalFromResultSet(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void updateEmissionGoal(EmissionGoal emissionGoal) {
    String sql = EmissionGoalCRUDQueries.UPDATE_GOAL.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, emissionGoal.getUserId());
      pstmt.setDouble(2, emissionGoal.getTargetEmission());
      pstmt.setDate(3, java.sql.Date.valueOf(emissionGoal.getStartDate()));
      pstmt.setDate(4, java.sql.Date.valueOf(emissionGoal.getEndDate()));
      pstmt.setString(5, emissionGoal.getStatus());
      pstmt.setLong(6, emissionGoal.getGoalId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteEmissionGoal(Long goalId) {
    String sql = EmissionGoalCRUDQueries.DELETE_GOAL.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, goalId);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private EmissionGoal extractEmissionGoalFromResultSet(ResultSet resultSet) throws SQLException {
    EmissionGoal emissionGoal = new EmissionGoal();
    emissionGoal.setGoalId(resultSet.getLong("goal_id"));
    emissionGoal.setUserId(resultSet.getLong("user_id"));
    emissionGoal.setTargetEmission(resultSet.getDouble("target_emission"));
    emissionGoal.setStartDate(resultSet.getDate("start_date").toLocalDate());
    emissionGoal.setEndDate(resultSet.getDate("end_date").toLocalDate());
    emissionGoal.setStatus(resultSet.getString("status"));

    return emissionGoal;
  }
}

