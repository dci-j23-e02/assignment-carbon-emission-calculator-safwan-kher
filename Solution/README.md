# Official Solution: Carbon Emission Calculator

## Step 1: Setting Up the PostgreSQL Environment

### SQL Script for Database and Tables Creation

```sql
-- Create the database
CREATE DATABASE carbon_emission;

-- Switch to the database
\c carbon_emission;

-- Create Users table
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);

-- Create Activities table
CREATE TABLE Activities (
    activity_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Create Emission_Factors table
CREATE TABLE Emission_Factors (
    factor_id SERIAL PRIMARY KEY,
    activity_id INT REFERENCES Activities(activity_id),
    factor DECIMAL NOT NULL,
    unit VARCHAR(50) NOT NULL
);

-- Create User_Emissions table
CREATE TABLE User_Emissions (
    emission_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id),
    activity_id INT REFERENCES Activities(activity_id),
    quantity DECIMAL NOT NULL,
    emission DECIMAL NOT NULL,
    date DATE NOT NULL
);

-- Create Emission_Goals table
CREATE TABLE Emission_Goals (
    goal_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(user_id),
    target_emission DECIMAL NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);
```

## Step 2: Implementing Model Classes in Java

### Java Model Classes

```java
public class User {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;

    // Getters and setters
}

public class Activity {
    private Long activityId;
    private String name;
    private String description;

    // Getters and setters
}

public class EmissionFactor {
    private Long factorId;
    private Long activityId;
    private Double factor;
    private String unit;

    // Getters and setters
}

public class UserEmission {
    private Long emissionId;
    private Long userId;
    private Long activityId;
    private Double quantity;
    private Double emission;
    private LocalDate date;

    // Getters and setters
}

public class EmissionGoal {
    private Long goalId;
    private Long userId;
    private Double targetEmission;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Getters and setters
}
```

## Step 3: DAO Interfaces and Implementations

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

Implementations will use JDBC to interact with the PostgreSQL database. Each method in the DAO implementation will use SQL queries to perform the necessary database operations. Use `PreparedStatement` to avoid SQL injection.

## Step 4: ConnectionFactory Class

```java
public class ConnectionFactory {
    private static Connection connection = null;

    private ConnectionFactory() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the PostgreSQL JDBC driver
                Class.forName("org.postgresql.Driver");
                // Create the connection instance
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/carbon_emission", "username", "password");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
```

## Step 5: SqlQueries Enums

### CRUDQueries Enum

```java
public enum CRUDQueries {
    INSERT_USER("INSERT INTO Users (username, email, password_hash) VALUES (?, ?, ?)"),
    GET_USER("SELECT * FROM Users WHERE user_id = ?"),
    UPDATE_USER("UPDATE Users SET username = ?, email = ?, password_hash = ? WHERE user_id = ?"),
    DELETE_USER("DELETE FROM Users WHERE user_id = ?");

    private String query;

    CRUDQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
```

### OperationQueries Enum

```java
public enum OperationQueries {
    CALCULATE_TOTAL_EMISSIONS_FOR_USER("SELECT SUM(emission) FROM User_Emissions WHERE user_id = ?"),
    COMPARE_EMISSIONS_BETWEEN_ACTIVITIES("SELECT activity_id, AVG(emission) FROM User_Emissions GROUP BY activity_id ORDER BY AVG(emission) DESC"),
    LIST_ACTIVITIES_ABOVE_THRESHOLD("SELECT * FROM Activities a JOIN User_Emissions ue ON a.activity_id = ue.activity_id WHERE ue.emission > ?");

    private String query;

    OperationQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
```

## Step 6: Implementing CRUD Operations in DAO Classes

Each DAO implementation will use the `CRUDQueries` enum to retrieve the appropriate SQL query, and then execute it using JDBC.

## Step 7: SQL Operations

Implement the SQL operations as described in the assignment using the `OperationQueries` enum.

## Step 8: Main Class for User Interaction

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

        // Further interaction logic here

        scanner.close();
    }
}
```

## Bonus Task: Visualization

For a simple visualization, you could use a third-party library like JFreeChart to create charts based on the emission data.

## Submission

Ensure that your GitHub repository contains all the necessary files, including the SQL scripts, Java source files, and any additional resources required to run the application. Include a README.md file with clear instructions on setting up and running the application.
