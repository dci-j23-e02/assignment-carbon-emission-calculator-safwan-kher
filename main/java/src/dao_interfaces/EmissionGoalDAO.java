package dao_interfaces;


import model_classes.EmissionGoal;

public interface EmissionGoalDAO {
  void insertEmissionGoal(EmissionGoal emissionGoal);
  EmissionGoal getEmissionGoal(Long goalId);
  void updateEmissionGoal(EmissionGoal emissionGoal);
  void deleteEmissionGoal(Long goalId);
}
