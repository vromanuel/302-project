package WisdomBites.tracker;
import java.time.LocalDate;

public class StudySession {
    private int id; // database ID
    private LocalDate sessionDate;
    private int durationMinutes; // how many minutes studied
    private boolean goalCompleted; // was daily goal completed?
    private String subject;
    private int weekNumber;

    // Constructor
    public StudySession(LocalDate sessionDate, int durationMinutes, boolean goalCompleted, String subject, int weekNumber){
        this.sessionDate = sessionDate;
        this.durationMinutes = durationMinutes;
        this.goalCompleted = goalCompleted;
        this.subject = subject;
        this.weekNumber = weekNumber;
    }

    // Getters and Setters
    public int getID(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public LocalDate getSessionDate(){
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isGoalCompleted() {
        return goalCompleted;
    }

    public void setGoalCompleted(boolean goalCompleted) {
        this.goalCompleted = goalCompleted;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }
}
