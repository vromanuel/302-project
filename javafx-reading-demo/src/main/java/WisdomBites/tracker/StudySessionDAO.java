package WisdomBites.tracker;

import WisdomBites.model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudySessionDAO {

    // Save a StudySession into the database
    public static boolean saveStudySession(StudySession session) {
        String sql = "INSERT INTO StudySessions(sessionDate, durationMinutes, goalCompleted, subject, weekNumber) VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, session.getSessionDate().toString());
            statement.setInt(2, session.getDurationMinutes());
            statement.setBoolean(3, session.isGoalCompleted());
            statement.setString(4, session.getSubject());
            statement.setInt(5, session.getWeekNumber());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;  // return true if insertion was successful

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
}
