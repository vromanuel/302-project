package WisdomBites.tracker;

import WisdomBites.model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudySessionDAO {

    private static Connection connection;

    public StudySessionDAO() {
        // Initialise the Study session Data Access object
        connection = DBConnection.getInstance();
        createStudySessionTable();
    }

    private void createStudySessionTable() {
        try {
            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS StudySessions (\n" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    sessionDate TEXT NOT NULL,\n" +
                    "    durationMinutes INTEGER NOT NULL,\n" +
                    "    goalCompleted BOOLEAN NOT NULL,\n" +
                    "    subject TEXT NOT NULL,\n" +
                    "    weekNumber INTEGER NOT NULL\n" +
                    ")";
            statement.execute(sql);

        } catch (Exception e)
        {
            e.printStackTrace();;
        }

    }

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
