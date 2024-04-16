package dao_interfaces;


import model_classes.EmissionFactor;

public interface EmissionFactorDAO {
  void insertEmissionFactor(EmissionFactor emissionFactor);
  EmissionFactor getEmissionFactor(Long factorId);
  void updateEmissionFactor(EmissionFactor emissionFactor);
  void deleteEmissionFactor(Long factorId);
}
