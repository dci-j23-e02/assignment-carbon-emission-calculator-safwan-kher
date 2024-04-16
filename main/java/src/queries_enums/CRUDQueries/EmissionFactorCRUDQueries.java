package queries_enums.CRUDQueries;

public enum EmissionFactorCRUDQueries {
  INSERT_EMISSION_FACTOR("INSERT INTO Emission_Factors(activity_id, factor, unit) VALUES (?, ?, ?)"),
  GET_EMISSION_FACTOR("SELECT * FROM Emission_Factors WHERE factor_id = ?"),
  GET_ALL_EMISSION_FACTORS("SELECT * FROM Emission_Factors"),
  UPDATE_EMISSION_FACTOR("UPDATE Emission_Factors SET activity_id = ?, factor = ?, unit = ? WHERE factor_id = ?"),
  DELETE_EMISSION_FACTOR("DELETE FROM Emission_Factors WHERE factor_id = ?");

  private String query;

  EmissionFactorCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
