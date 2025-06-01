package WisdomBites.model;

public class Unit {
    private int id;
    private int userId;
    private String unitName;
    private int weekNumber;
    private String taskDescription;
    private String completed;

    public Unit(int id, int userId, String unitName, int weekNumber,
                String taskDescription, String completed) {
        this(userId, unitName, weekNumber, taskDescription, completed);
        this.id = id;
    }

    public Unit(int userId, String unitName, int weekNumber,
                String taskDescription, String completed) {
        this.userId = userId;
        this.unitName = unitName;
        this.weekNumber = weekNumber;
        this.taskDescription = taskDescription;
        this.completed = completed;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getUnitName() { return unitName; }
    public int getWeekNumber() { return weekNumber; }
    public String getTaskDescription() { return taskDescription; }
    public boolean isCompleted() { return "T".equals(completed); }

    // Setters
    public void setUnitName(String unitName) { this.unitName = unitName; }
    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }
    public void setCompleted(boolean completed) { this.completed = completed ? "T" : "F"; }
}