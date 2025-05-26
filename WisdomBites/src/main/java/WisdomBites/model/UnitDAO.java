package WisdomBites.model;

import WisdomBites.controller.StateController;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitDAO {
    private Connection connection;

    public UnitDAO() {
        connection = DBConnection.getInstance();
        createStudyUnitTable();
    }

    private void createStudyUnitTable() {
        try {
            Statement statement = connection.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS study_units (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "userId INTEGER NOT NULL," +
                    "unitName TEXT NOT NULL," +
                    "weekNumber INTEGER NOT NULL," +
                    "taskDescription TEXT," +
                    "completed TEXT CHECK (completed IN ('T', 'F')) DEFAULT 'F'," +
                    "FOREIGN KEY (userId) REFERENCES user(id)," +
                    "UNIQUE (userId, unitName, weekNumber)" +
                    ")";

            statement.execute(query);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public List<Unit> getUnitsByName(int userId, String unitName) {

        List<Unit> units = new ArrayList<>();

        try {

            String sql = "SELECT * FROM study_units WHERE " +
                    "userId = ? AND " +
                    "unitName = ? ORDER BY weekNumber";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, userId);
            statement.setString(2, unitName);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                units.add(new Unit(
                        rs.getInt("userId"),
                        rs.getString("unitName"),
                        rs.getInt("weekNumber"),
                        rs.getString("taskDescription"),
                        rs.getString("completed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    public List<Unit> getUnitsForUser(int userId) {
        List<Unit> units = new ArrayList<>();
        try {
            String query = "SELECT * FROM study_units WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Unit unit = new Unit(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getString("unitName"),
                        resultSet.getInt("weekNumber"),
                        resultSet.getString("taskDescription"),
                        resultSet.getString("completed")
                );
                units.add(unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    public Map<String, List<Unit>> getUnitsGroupedByName(int userId) {
        Map<String, List<Unit>> unitsMap = new HashMap<>();
        List<Unit> units = getUnitsForUser(userId);

        for (Unit unit : units) {
            unitsMap.computeIfAbsent(unit.getUnitName(), k -> new ArrayList<>()).add(unit);
        }

        return unitsMap;
    }

    public boolean updateTaskCompletion(int unitId, boolean completed) {
        try {
            String query = "UPDATE study_units SET completed = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, completed ? "T" : "F");
            statement.setInt(2, unitId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUnit(int unitId) {
        try {
            String query = "DELETE FROM study_units WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, unitId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUnitName(int userId, String oldName, String newName) {
        try {
            String query = "UPDATE study_units SET unitName = ? WHERE userId = ? AND unitName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newName);
            statement.setInt(2, userId);
            statement.setString(3, oldName);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveUnit(int userId, String unitName, int weekNumber, String taskDescription, boolean completed) {
        try {
            // First try to update existing record
            String updateQuery = "UPDATE study_units SET taskDescription = ?, completed = ? " +
                    "WHERE userId = ? AND unitName = ? AND weekNumber = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setString(1, taskDescription);
            updateStmt.setString(2, completed ? "T" : "F");
            updateStmt.setInt(3, userId);
            updateStmt.setString(4, unitName);
            updateStmt.setInt(5, weekNumber);

            int rowsUpdated = updateStmt.executeUpdate();

            // If no record was updated, insert new one
            if (rowsUpdated == 0) {
                String insertQuery = "INSERT INTO study_units " +
                        "(userId, unitName, weekNumber, taskDescription, completed) " +
                        "VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
                insertStmt.setInt(1, userId);
                insertStmt.setString(2, unitName);
                insertStmt.setInt(3, weekNumber);
                insertStmt.setString(4, taskDescription);
                insertStmt.setString(5, completed ? "T" : "F");

                insertStmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}