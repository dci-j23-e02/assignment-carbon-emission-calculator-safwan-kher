package model_classes;

public class Activity {
// Attributes
  private  Long activityId;
  private String name;
  private String description;

  // Constructors

  public Activity() {
  }

  public Activity(Long activityId, String name, String description) {
    this.activityId = activityId;
    this.name = name;
    this.description = description;
  }

  public Activity(String name, String description) {
    this.name = name;
    this.description = description;
  }


  // getters and setters

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // toString()

  @Override
  public String toString() {
    return "Activity{" +
        "activityId=" + activityId +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
