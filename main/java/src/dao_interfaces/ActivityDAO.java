package dao_interfaces;

import  model_classes.Activity;

public interface ActivityDAO {
  void insertActivity(Activity activity);
  Activity getActivity(Long activityId);
  void updateActivity(Activity activity);
  void deleteActivity(Long activityId);
}
