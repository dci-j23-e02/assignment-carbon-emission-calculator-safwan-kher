package model_classes;

import java.time.LocalDate;

public class EmissionGoal {
// Attributes
private Long goalId;
  private Long userId;
  private Double targetEmission;
  private LocalDate startDate;
  private LocalDate endDate;
  private String status;

  // Constructors


  public EmissionGoal() {
  }


  public EmissionGoal(Long goalId, Long userId, Double targetEmission, LocalDate startDate,
      LocalDate endDate, String status) {
    this.goalId = goalId;
    this.userId = userId;
    this.targetEmission = targetEmission;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  public EmissionGoal(Long userId, Double targetEmission, LocalDate startDate,
      LocalDate endDate, String status) {
    this.userId = userId;
    this.targetEmission = targetEmission;
    this.startDate = startDate;
    this.endDate = endDate;
    this.status = status;
  }

  // getters and setters

  public Long getGoalId() {
    return goalId;
  }

  public void setGoalId(Long goalId) {
    this.goalId = goalId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Double getTargetEmission() {
    return targetEmission;
  }

  public void setTargetEmission(Double targetEmission) {
    this.targetEmission = targetEmission;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  // toString()

  @Override
  public String toString() {
    return "EmissionGoal{" +
        "goalId=" + goalId +
        ", userId=" + userId +
        ", targetEmission=" + targetEmission +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", status='" + status + '\'' +
        '}';
  }
}
