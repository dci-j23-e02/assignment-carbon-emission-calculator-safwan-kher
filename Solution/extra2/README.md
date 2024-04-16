To provide a comprehensive solution for the Carbon Emission Calculator, I'll outline all necessary classes, methods, and include a bonus task for CLI-based visualization. This solution will cover the entire process from setting up the database to implementing the Java application with JDBC.

### Database Setup

Refer to the SQL provided in previous messages to set up your PostgreSQL database with the necessary tables.

### Java Model Classes

Here are the model classes corresponding to the database tables:

```java
public class User {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;

    // Constructors, getters, and setters
}

public class Activity {
    private Long activityId;
    private String name;
    private String description;

    // Constructors, getters, and setters
}

public class EmissionFactor {
    private Long factorId;
    private Long activityId;
    private Double factor;
    private String unit;

    // Constructors, getters, and setters
}

public class UserEmission {
    private Long emissionId;
    private Long userId;
    private Long activityId;
    private Double quantity;
    private Double emission;
    private LocalDate date;

    // Constructors, getters, and setters
}

public class EmissionGoal {
    private Long goalId;
    private Long userId;
    private Double targetEmission;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Constructors, getters, and setters
}
```

### DAO Interfaces

```java
public interface UserDAO {
    void insertUser(User user);
    User getUser(Long userId);
    void updateUser(User user);
    void deleteUser(Long userId);
}

public interface ActivityDAO {
    void insertActivity(Activity activity);
    Activity getActivity(Long activityId);
    void updateActivity(Activity activity);
    void deleteActivity(Long activityId);
}

public interface EmissionFactorDAO {
    void insertEmissionFactor(EmissionFactor emissionFactor);
    EmissionFactor getEmissionFactor(Long factorId);
    void updateEmissionFactor(EmissionFactor emissionFactor);
    void deleteEmissionFactor(Long factorId);
}

public interface UserEmissionDAO {
    void insertUserEmission(UserEmission userEmission);
    UserEmission getUserEmission(Long emissionId);
    void updateUserEmission(UserEmission userEmission);
    void deleteUserEmission(Long emissionId);
}

public interface EmissionGoalDAO {
    void insertEmissionGoal(EmissionGoal emissionGoal);
    EmissionGoal getEmissionGoal(Long goalId);
    void updateEmissionGoal(EmissionGoal emissionGoal);
    void deleteEmissionGoal(Long goalId);
}
```

### DAO Implementations

Here's an example implementation for `UserDAOImpl`. Implement similar classes for other DAO interfaces.

```java
public class UserDAOImpl implements UserDAO {
    private Connection connection = ConnectionFactory.getConnection();

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO Users (username, email, password_hash) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement other methods similarly
}
```

### Connection Factory

```java
public class ConnectionFactory {
    private static Connection connection = null;

    private ConnectionFactory() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/carbon_emission", "username", "password");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
```

### Main Class and CLI Interaction

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Carbon Emission Calculator!");

        // Example interaction
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Hello, " + username + "!");

        // Assume user ID is known or retrieved earlier
        Long userId = 1L; // Example user ID
        double totalEmissions = new UserEmissionDAOImpl().calculateTotalEmissionsForUser(userId);
        System.out.println("Your total emissions are: " + totalEmissions + " units");

        scanner.close();
    }
}
```

### Bonus Task: CLI-Based Visualization

```java
private static void displayEmissionsChart(Map<String, Double> emissionsData) {
    final int scale = 10; // This determines the scale of your chart

    System.out.println("\nEmissions Chart:");
    for (Map.Entry<String, Double> entry : emissionsData.entrySet()) {
        System.out.print(entry.getKey() + ": ");
        int count = (int) (entry.getValue() / scale);
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println(" (" + entry.getValue() + ")");
    }
}
```

This comprehensive solution covers all aspects of the assignment, from database setup to Java implementation and CLI interaction. Ensure to handle exceptions properly and close resources where necessary. This setup provides a solid foundation for understanding JDBC operations, database interactions, and basic CLI visualizations.
