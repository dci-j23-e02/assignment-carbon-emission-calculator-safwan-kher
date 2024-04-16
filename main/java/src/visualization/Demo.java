package visualization;


import java.util.List;

public class Demo {
  public static void visualizeCarbonEmissions(List<Integer> emissions) {
    System.out.println("Carbon Emissions Visualization:");
    System.out.println("--------------------------------");

    int maxEmission = getMaxEmission(emissions);
    int height = 10; // Maximum height of the chart

    for (int i = height; i > 0; i--) {
      for (int emission : emissions) {
        char symbol = getSymbol(emission, maxEmission, i);
        System.out.print(symbol);
      }
      System.out.println();
    }

    // Print x-axis labels
    for (int i = 0; i < emissions.size(); i++) {
      System.out.print("|");
    }
    System.out.println("\n");

    // Print y-axis labels
    for (int i = height; i >= 0; i--) {
      System.out.println(i * maxEmission / height);
    }
    System.out.println();
  }

  private static char getSymbol(int emission, int maxEmission, int height) {
    double ratio = (double) emission / maxEmission;
    double threshold = (double) height / 10;
    if (ratio >= threshold) {
      return '|'; // High emission
    } else {
      return ' '; // Empty space
    }
  }

  private static int getMaxEmission(List<Integer> emissions) {
    int maxEmission = Integer.MIN_VALUE;
    for (int emission : emissions) {
      if (emission > maxEmission) {
        maxEmission = emission;
      }
    }
    return maxEmission;
  }

  public static void main(String[] args) {
    // Example usage:
    List<Integer> emissions = List.of(5, 8, 12, 6, 10); // Sample emissions data
    visualizeCarbonEmissions(emissions);
  }
}

