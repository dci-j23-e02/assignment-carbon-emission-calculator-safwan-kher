package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao_interfaces.ActivityDAO;
import model_classes.Activity;
import queries_enums.CRUDQueries.ActivityCRUDQueries;
import util.ConnectionFactory;

public class ActivityDAOImpl implements ActivityDAO {

  @Override
  public void insertActivity(Activity activity) {
    Connection conn = ConnectionFactory.getConnection();
    try (PreparedStatement pstmt = conn.prepareStatement(ActivityCRUDQueries.INSERT_ACTIVITY.getQuery())) {
      pstmt.setString(1, activity.getName());
      pstmt.setString(2, activity.getDescription());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Activity getActivity(Long activityId) {
    Connection conn = ConnectionFactory.getConnection();
    Activity activity = null;
    try (PreparedStatement pstmt = conn.prepareStatement(ActivityCRUDQueries.GET_ACTIVITY.getQuery())) {
      pstmt.setLong(1, activityId);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        activity = new Activity();
        activity.setActivityId(rs.getLong("activity_id"));
        activity.setName(rs.getString("name"));
        activity.setDescription(rs.getString("description"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return activity;
  }

  @Override
  public void updateActivity(Activity activity) {
    Connection conn = ConnectionFactory.getConnection();
    try (PreparedStatement pstmt = conn.prepareStatement(ActivityCRUDQueries.UPDATE_ACTIVITY.getQuery())) {
      pstmt.setString(1, activity.getName());
      pstmt.setString(2, activity.getDescription());
      pstmt.setLong(3, activity.getActivityId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteActivity(Long activityId) {
    Connection conn = ConnectionFactory.getConnection();
    try (PreparedStatement pstmt = conn.prepareStatement(ActivityCRUDQueries.DELETE_ACTIVITY.getQuery())) {
      pstmt.setLong(1, activityId);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
