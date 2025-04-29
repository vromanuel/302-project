package WisdomBites.model;

public class Task {

    private int id;
    private int createdBy;
    private String dateCreated;
    private String title;
    private String description;
    private boolean completed;

    public Task(int createdBy, String dateCreated, String title, String description, boolean completed)
    {
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public String getDateCreated() {return dateCreated;}
    public void setDateCreated(int createdBy) {this.createdBy = createdBy; }

    public String getTitle() { return title; }
    public void setTitle(String title) {this.title = title;}

    public String description() {return }
}


