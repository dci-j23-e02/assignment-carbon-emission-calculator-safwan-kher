package dao_interfaces;

import java.sql.SQLException;
import java.util.List;
import model_classes.UserEmission;
import services.ActivityEmission;


public interface OperationDAO {
  /**
   * Calculates the total emissions for a specific user.
   * @param userId the ID of the user
   * @return the total emissions for the user
   * @throws SQLException if a database access error occurs
   */
  double calculateTotalEmissionsForUser(Long userId) throws SQLException;

  /**
   * Compares emissions between different activities.
   * @return a List of Activity objects with their average emissions, sorted by emissions in descending order
   * @throws SQLException if a database access error occurs
   */
  List<ActivityEmission> compareEmissionsBetweenActivities() throws SQLException;

  /**
   * Lists all activities where emissions are above a specified threshold.
   * @param threshold the emission threshold
   * @return a List of UserEmission objects for emissions above the given threshold
   * @throws SQLException if a database access error occurs
   */
  List<UserEmission> listActivitiesAboveThreshold(double threshold) throws SQLException;
}
