package model_classes;

public class EmissionFactor {
  // Attributes
private Long factorId;
  private Long activityId;
  private Double factor;
  private String unit;

  // Constructors


  public EmissionFactor() {
  }

  public EmissionFactor(Long factorId, Long activityId, Double factor, String unit) {
    this.factorId = factorId;
    this.activityId = activityId;
    this.factor = factor;
    this.unit = unit;
  }

  public EmissionFactor(Long activityId, Double factor, String unit) {
    this.activityId = activityId;
    this.factor = factor;
    this.unit = unit;
  }

  // getters and setters

  public Long getFactorId() {
    return factorId;
  }

  public void setFactorId(Long factorId) {
    this.factorId = factorId;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Double getFactor() {
    return factor;
  }

  public void setFactor(Double factor) {
    this.factor = factor;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  // toString()

  @Override
  public String toString() {
    return "EmissionFactor{" +
        "factorId=" + factorId +
        ", activityId=" + activityId +
        ", factor=" + factor +
        ", unit='" + unit + '\'' +
        '}';
  }
}
