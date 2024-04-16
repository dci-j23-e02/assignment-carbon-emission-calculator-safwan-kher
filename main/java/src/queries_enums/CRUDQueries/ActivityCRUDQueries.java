package queries_enums.CRUDQueries;

public enum ActivityCRUDQueries {
  INSERT_ACTIVITY("INSERT INTO Activities(name, description) VALUES (?, ?)"),
  GET_ACTIVITY("SELECT * FROM Activities WHERE activity_id = ?"),
  GET_ALL_ACTIVITIES("SELECT * FROM Activities"),
  UPDATE_ACTIVITY("UPDATE Activities SET name = ?, description = ? WHERE activity_id = ?"),
  DELETE_ACTIVITY("DELETE FROM Activities WHERE activity_id = ?");

  private String query;

  ActivityCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
