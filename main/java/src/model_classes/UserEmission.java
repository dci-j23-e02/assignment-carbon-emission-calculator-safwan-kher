package model_classes;

import java.time.LocalDate;

public class UserEmission {
// Attributes
private Long emissionId;
  private Long userId;
  private Long activityId;
  private Double quantity;
  private Double emission;
  private LocalDate date;

  // Constructors

  public UserEmission() {
  }

  public UserEmission(Long emissionId, Long userId, Long activityId, Double quantity,
      Double emission, LocalDate date) {
    this.emissionId = emissionId;
    this.userId = userId;
    this.activityId = activityId;
    this.quantity = quantity;
    this.emission = emission;
    this.date = date;
  }

  public UserEmission(Long userId, Long activityId, Double quantity, Double emission,
      LocalDate date) {
    this.userId = userId;
    this.activityId = activityId;
    this.quantity = quantity;
    this.emission = emission;
    this.date = date;
  }

  // getters and setters

  public Long getEmissionId() {
    return emissionId;
  }

  public void setEmissionId(Long emissionId) {
    this.emissionId = emissionId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public Double getEmission() {
    return emission;
  }

  public void setEmission(Double emission) {
    this.emission = emission;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  // toString()

  @Override
  public String toString() {
    return "UserEmission{" +
        "emissionId=" + emissionId +
        ", userId=" + userId +
        ", activityId=" + activityId +
        ", quantity=" + quantity +
        ", emission=" + emission +
        ", date=" + date +
        '}';
  }
}
