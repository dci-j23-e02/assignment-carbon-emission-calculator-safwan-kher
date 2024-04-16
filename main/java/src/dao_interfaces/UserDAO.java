package dao_interfaces;

import model_classes.User;

public interface UserDAO {
  void insertUser(User user);
  User getUser(Long userId);
  void updateUser(User user);
  void deleteUser(Long userId);
}
