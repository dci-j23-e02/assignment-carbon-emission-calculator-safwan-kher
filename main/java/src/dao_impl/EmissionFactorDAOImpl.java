package dao_impl;

import dao_interfaces.EmissionFactorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model_classes.EmissionFactor;
import queries_enums.CRUDQueries.EmissionFactorCRUDQueries;
import util.ConnectionFactory;


public class EmissionFactorDAOImpl implements EmissionFactorDAO {

  @Override
  public void insertEmissionFactor(EmissionFactor emissionFactor) {
    Connection connection = ConnectionFactory.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(EmissionFactorCRUDQueries.INSERT_EMISSION_FACTOR.getQuery())) {
      ps.setLong(1, emissionFactor.getActivityId());
      ps.setDouble(2, emissionFactor.getFactor());
      ps.setString(3, emissionFactor.getUnit());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public EmissionFactor getEmissionFactor(Long factorId) {
    Connection connection = ConnectionFactory.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(EmissionFactorCRUDQueries.GET_EMISSION_FACTOR.getQuery())) {
      ps.setLong(1, factorId);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new EmissionFactor(
            rs.getLong("factor_id"),
            rs.getLong("activity_id"),
            rs.getDouble("factor"),
            rs.getString("unit")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void updateEmissionFactor(EmissionFactor emissionFactor) {
    Connection connection = ConnectionFactory.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(EmissionFactorCRUDQueries.UPDATE_EMISSION_FACTOR.getQuery())) {
      ps.setLong(1, emissionFactor.getActivityId());
      ps.setDouble(2, emissionFactor.getFactor());
      ps.setString(3, emissionFactor.getUnit());
      ps.setLong(4, emissionFactor.getFactorId());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteEmissionFactor(Long factorId) {
    Connection connection = ConnectionFactory.getConnection();
    try (PreparedStatement ps = connection.prepareStatement(EmissionFactorCRUDQueries.DELETE_EMISSION_FACTOR.getQuery())) {
      ps.setLong(1, factorId);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
