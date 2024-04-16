package queries_enums.CRUDQueries;

public enum UserCRUDQueries {
  INSERT_USER("INSERT INTO Users(username, email, password_hash) VALUES (?, ?, ?)"),
  GET_USER("SELECT * FROM Users WHERE user_id = ?"),
  GET_ALL_USERS("SELECT * FROM Users")  ,
  UPDATE_USER("UPDATE Users SET username = ? , email = ? , password_hash = ? WHERE user_id =?"),
  DELETE_USER("DELETE FROM Users WHERE user_id = ?");

private  String query;

  UserCRUDQueries(String query) {
    this.query = query;
  }

  public String getQuery() {
    return query;
  }
}
