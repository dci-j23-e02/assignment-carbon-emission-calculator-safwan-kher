package dao_impl;

import dao_interfaces.OperationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model_classes.UserEmission;
import queries_enums.OperationQueries;
import services.ActivityEmission;
import util.ConnectionFactory;

public class OperationDAOImpl implements OperationDAO {
  private Connection connection = ConnectionFactory.getConnection();
  /**
   * Calculates the total emissions for a specific user.
   *
   * @param userId the ID of the user
   * @return the total emissions for the user
   * @throws SQLException if a database access error occurs
   */
  @Override
  public double calculateTotalEmissionsForUser(Long userId) throws SQLException {
    double totalEmissions = 0;
    String sql = OperationQueries.CALCULATE_TOTAL_EMISSIONS_FOR_USER.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setLong(1, userId);
      ResultSet resultSet = pstmt.executeQuery();
      if (resultSet.next()) {
        totalEmissions = resultSet.getDouble(1);
      }
    }
    return totalEmissions;
  }

  /**
   * Compares emissions between different activities.
   *
   * @return a List of Activity objects with their average emissions, sorted by emissions in
   * descending order
   * @throws SQLException if a database access error occurs
   */
  @Override
  public List<ActivityEmission> compareEmissionsBetweenActivities() throws SQLException {
    List<ActivityEmission> activityEmissions = new ArrayList<>();
    String sql = OperationQueries.COMPARE_EMISSIONS_BETWEEN_ACTIVITIES.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = pstmt.executeQuery();
      while (resultSet.next()) {
        long activityId = resultSet.getLong(1);
        double avgEmission = resultSet.getDouble(2);
        activityEmissions.add(new ActivityEmission(activityId, avgEmission));
      }
    }
    return activityEmissions;
  }

  /**
   * Lists all activities where emissions are above a specified threshold.
   *
   * @param threshold the emission threshold
   * @return a List of UserEmission objects for emissions above the given threshold
   * @throws SQLException if a database access error occurs
   */
  @Override
  public List<UserEmission> listActivitiesAboveThreshold(double threshold) throws SQLException {
    List<UserEmission> userEmissions = new ArrayList<>();
    String sql = OperationQueries.LIST_ACTIVITIES_ABOVE_THRESHOLD.getQuery();
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setDouble(1, threshold);
      ResultSet resultSet = pstmt.executeQuery();
      while (resultSet.next()) {
        userEmissions.add(extractUserEmissionFromResultSet(resultSet));
      }
    }
    return userEmissions;
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
