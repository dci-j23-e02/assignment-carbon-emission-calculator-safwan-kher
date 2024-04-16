package model_classes;

public class User {
  // Attributes
  private Long userId;
  private String userName;
  private String email;
  private String passwordHash;


  // Constructors
  public User() {
  }

  public User(Long userId, String userName, String email, String passwordHash) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
    this.passwordHash = passwordHash;
  }

  public User(String userName, String email, String passwordHash) {
    this.userName = userName;
    this.email = email;
    this.passwordHash = passwordHash;
  }

  // getters and setters

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  // toString()

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", userName='" + userName + '\'' +
        ", email='" + email + '\'' +
        ", passwordHash='" + passwordHash + '\'' +
        '}';
  }
}
