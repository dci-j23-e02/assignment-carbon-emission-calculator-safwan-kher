import dao_impl.ActivityDAOImpl;
import dao_impl.EmissionFactorDAOImpl;
import dao_impl.EmissionGoalDAOImpl;
import dao_impl.OperationDAOImpl;
import dao_impl.UserDAOImpl;
import dao_impl.UserEmissionDAOImpl;
import java.sql.SQLException;
import java.util.List;
import model_classes.Activity;
import model_classes.EmissionFactor;
import model_classes.EmissionGoal;
import model_classes.User;
import model_classes.UserEmission;
import services.ActivityEmission;

public class Main {
  public static void main(String[] args) {
    // Example usage of DAO classes

    // Creating instances of DAO implementations
    UserDAOImpl userDAO = new UserDAOImpl();
    ActivityDAOImpl activityDAO = new ActivityDAOImpl();
    UserEmissionDAOImpl userEmissionDAO = new UserEmissionDAOImpl();
    EmissionGoalDAOImpl emissionGoalDAO = new EmissionGoalDAOImpl();
    EmissionFactorDAOImpl emissionFactorDAO = new EmissionFactorDAOImpl();
    OperationDAOImpl operationDAO = new OperationDAOImpl();

    try {
      // Inserting a user
      User user = new User("example_user", "example@example.com", "password");
      userDAO.insertUser(user);

      // Inserting an activity
      Activity activity = new Activity("Example Activity", "Description");
      activityDAO.insertActivity(activity);

      // Inserting a user emission
      UserEmission userEmission = new UserEmission(1L, 1L, 10.0, 5.0, java.time.LocalDate.now());
      userEmissionDAO.insertUserEmission(userEmission);

      // Inserting an emission goal
      EmissionGoal emissionGoal = new EmissionGoal(1L, 100.0, java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(30), "Pending");
      emissionGoalDAO.insertEmissionGoal(emissionGoal);

      // Inserting an emission factor
      EmissionFactor emissionFactor = new EmissionFactor(1L, 2.5, "kg CO2");
      emissionFactorDAO.insertEmissionFactor(emissionFactor);

      // Retrieving total emissions for a user
      double totalEmissions = operationDAO.calculateTotalEmissionsForUser(1L);
      System.out.println("Total emissions for user: " + totalEmissions);

      // Comparing emissions between activities
      List<ActivityEmission> activityEmissions = operationDAO.compareEmissionsBetweenActivities();
      System.out.println("Activity Emissions:");
      for (ActivityEmission ae : activityEmissions) {
        System.out.println("Activity ID: " + ae.getActivity() + ", Average Emission: " + ae.getAverageEmission());
      }

      // Listing activities above a threshold
      List<UserEmission> userEmissionsAboveThreshold = operationDAO.listActivitiesAboveThreshold(3.0);
      System.out.println("User Emissions above threshold:");
      for (UserEmission ue : userEmissionsAboveThreshold) {
        System.out.println(ue.toString());
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
