
### Step 2: Implementing Getters and Setters for Model Classes

Here's an example for the `User` class. Similar methods should be implemented for other model classes.

```java
public class User {
    private Long userId;
    private String username;
    private String email;
    private String passwordHash;

    // Getters
    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
```

### Step 3: DAO Implementations

Here's an example implementation for the `UserDAO` interface using JDBC.

```java
public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void insertUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement(CRUDQueries.INSERT_USER.getQuery())) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(Long userId) {
        User user = new User();
        try (PreparedStatement ps = connection.prepareStatement(CRUDQueries.GET_USER.getQuery())) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement(CRUDQueries.UPDATE_USER.getQuery())) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setLong(4, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long userId) {
        try (PreparedStatement ps = connection.prepareStatement(CRUDQueries.DELETE_USER.getQuery())) {
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### Step 6: Implementing CRUD Operations in DAO Classes

The above `UserDAOImpl` class is an example of how CRUD operations are implemented using the `CRUDQueries` enum. Similar implementations should be created for other DAO interfaces (`ActivityDAO`, `EmissionFactorDAO`, etc.).

### Step 7: SQL Operations

Here's how you might implement a method to calculate total emissions for a user:

```java
public double calculateTotalEmissionsForUser(Long userId) {
    double totalEmissions = 0;
    try (PreparedStatement ps = connection.prepareStatement(OperationQueries.CALCULATE_TOTAL_EMISSIONS_FOR_USER.getQuery())) {
        ps.setLong(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            totalEmissions = rs.getDouble(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalEmissions;
}
```

### Main Class: Further Interaction Logic

```java
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

### Bonus Task: Visualization Using JFreeChart

To visualize emissions data, you can use JFreeChart. First, add the JFreeChart library to your project. Then, you can create a simple bar chart:

```java
public void displayEmissionChart(Map<String, Double> emissionData) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (Map.Entry<String, Double> entry : emissionData.entrySet()) {
        dataset.addValue(entry.getValue(), "Emissions", entry.getKey());
    }

    JFreeChart chart = ChartFactory.createBarChart(
        "Carbon Emissions", "Activity", "Emissions", dataset,
        PlotOrientation.VERTICAL, false, true, false);

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
    JFrame frame = new JFrame();
    frame.add(chartPanel);
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
```

This method assumes you have a map of activity names to emission values, which you could gather from your database using appropriate queries.

### Create a simple CLI-based visualization using symbols like `|`, `*`, `-`, and `_`

To create a simple CLI-based visualization using symbols like `|`, `*`, `-`, and `_`, you can generate a text-based histogram or bar chart. This approach is straightforward and doesn't require any additional libraries. Below is an example of how you might implement this in your main class to display emissions data.

### Step: Visualization Using CLI

First, let's assume you have a method in your `UserEmissionDAOImpl` that retrieves emissions data for visualization. This method might return a `Map<String, Double>` where keys are activity names and values are the corresponding emission amounts.

Here's how you could implement a simple CLI visualization:

```java
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Carbon Emission Calculator!");

        // Example interaction
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Hello, " + username + "!");

        // Display emissions chart
        Map<String, Double> emissionsData = getEmissionsData(); // This method should be implemented to fetch data
        displayEmissionsChart(emissionsData);

        scanner.close();
    }

    private static Map<String, Double> getEmissionsData() {
        // This should interact with your DAO to fetch data
        // Here's a dummy implementation for demonstration
        Map<String, Double> data = new HashMap<>();
        data.put("Car", 150.0);
        data.put("Bus", 50.0);
        data.put("Bike", 5.0);
        return data;
    }

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
}
```

### Explanation:

1. **Main Method**: The main method handles user interaction and calls other methods to fetch data and display the chart.
2. **getEmissionsData Method**: This method should ideally interact with your DAO to fetch real emissions data. For demonstration, it returns a hardcoded map of activities and their corresponding emissions.
3. **displayEmissionsChart Method**: This method takes the emissions data and prints a simple bar chart. Each bar is represented by a series of `*` characters. The length of each bar is determined by the emission value divided by a scale factor, making the chart fit better in the console.

### Output Example:

```
Welcome to the Carbon Emission Calculator!
Enter your username:
John
Hello, John!

Emissions Chart:
Car: *************** (150.0)
Bus: ***** (50.0)
Bike: * (5.0)
```

This CLI-based visualization provides a simple and immediate way to visually interpret the data without needing any graphical interface, making it suitable for console applications. Adjust the `scale` variable to fit your specific output needs or to handle larger numbers more compactly.

This completes the detailed step-by-step implementation of the Carbon Emission Calculator assignment.
