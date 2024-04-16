package services;

/**
 * Helper class to associate an Activity with its average emission.
 */
public class ActivityEmission {
  private Long activity;
  private Double averageEmission;

  // Constructor, getters, and setters
  public ActivityEmission(long activity, Double averageEmission) {
    this.activity = activity;
    this.averageEmission = averageEmission;
  }

  public Long getActivity() {
    return activity;
  }

  public void setActivity(Long activity) {
    this.activity = activity;
  }

  public Double getAverageEmission() {
    return averageEmission;
  }

  public void setAverageEmission(Double averageEmission) {
    this.averageEmission = averageEmission;
  }
}
