import WisdomBites.model.Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void basicTaskCreationWorks() {
        Task task = new Task(1, "2024-01-01", "Learn Java", "Study collections", "F");

        assertEquals(1, task.getCreatedBy());
        assertEquals("2024-01-01", task.getDateCreated());
        assertEquals("Learn Java", task.getTitle());
        assertEquals("Study collections", task.getDescription());
        assertEquals("F", task.getCompleted());
    }

    @Test
    public void settingNewValuesWorks() {
        Task task = new Task(1, "2024-01-01", "Old", "Old", "F");

        task.setId(5);
        task.setCreatedBy(2);
        task.setTitle("New");
        task.setDescription("New");
        task.setCompleted();

        assertEquals(5, task.getId());
        assertEquals(2, task.getCreatedBy());
        assertEquals("New", task.getTitle());
        assertEquals("New", task.getDescription());
        assertEquals("T", task.getCompleted());
    }

    @Test
    public void settingEmptyValuesWorks() {
        Task task = new Task(1, "", "", "", "");

        assertEquals("", task.getTitle());
        assertEquals("", task.getDescription());
        assertEquals("", task.getCompleted());
    }

    @Test
    public void settingNullValuesWorks() {
        Task task = new Task(1, null, null, null, null);

        assertNull(task.getTitle());
        assertNull(task.getDescription());
        assertNull(task.getCompleted());
    }

    @Test
    public void markCompletedSetsToT() {
        Task task = new Task(1, "2024-01-01", "Task", "Desc", "F");

        task.setCompleted();
        assertEquals("T", task.getCompleted());
    }
}