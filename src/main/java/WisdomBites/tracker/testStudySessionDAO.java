package WisdomBites.tracker;

import java.time.LocalDate;

public class testStudySessionDAO {
    public static void main(String[] args){
        // create a test study session

        StudySessionDAO studySessionDAO = new StudySessionDAO();
        StudySession testSession = new StudySession(
            LocalDate.now(),
            120,    // studies 120 minutes
            true,                 // goal completed
            "CAB301",             // subject name
            5                    // week number


        );

        // save it into the database
        boolean success = StudySessionDAO.saveStudySession(testSession);

        // print result
        if (success) {
            System.out.println("StudySession saved successfully.");
        } else {
            System.out.println("failed to save.");
        }
    }
}
