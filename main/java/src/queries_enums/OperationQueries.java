package queries_enums;

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
