package queries_enums.CRUDQueries;

public enum UserEmissionCRUDQueries {
  INSERT_USER_EMISSION("INSERT INTO User_Emissions(user_id, activity_id, quantity, emission, date) VALUES (?, ?, ?, ?, ?)"),
  GET_USER_EMISSION("SELECT * FROM User_Emissions WHERE emission_id = ?"),
  GET_ALL_USER_EMISSIONS("SELECT * FROM User_Emissions"),
  UPDATE_USER_EMISSION("UPDATE User_Emissions SET user_id = ?, activity_id = ?, quantity = ?, emission = ?, date = ? WHERE emission_id = ?"),
  DELETE_USER_EMISSION("DELETE FROM User_Emissions WHERE emission_id = ?");

  private String query;

  UserEmissionCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
