package queries_enums.CRUDQueries;

public enum EmissionGoalCRUDQueries {
  INSERT_GOAL("INSERT INTO Emission_Goals(user_id, target_emission, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)"),
  GET_GOAL("SELECT * FROM Emission_Goals WHERE goal_id = ?"),
  GET_ALL_GOALS("SELECT * FROM Emission_Goals"),
  UPDATE_GOAL("UPDATE Emission_Goals SET user_id = ?, target_emission = ?, start_date = ?, end_date = ?, status = ? WHERE goal_id = ?"),
  DELETE_GOAL("DELETE FROM Emission_Goals WHERE goal_id = ?");

  private String query;

  EmissionGoalCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
