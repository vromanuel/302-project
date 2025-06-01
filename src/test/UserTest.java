import WisdomBites.model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    private final String TEST_FIRST_NAME = "John";
    private final String TEST_LAST_NAME = "Doe";
    private final String TEST_EMAIL = "john.doe@example.com";
    private final String TEST_PASSWORD = "securePassword123";

    @BeforeEach
    public void setUp() {
        user = new User(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
    }


    @Test
    public void testUserCreation() {
        assertNotNull(user);
        assertEquals(TEST_FIRST_NAME, user.getFirstName());
        assertEquals(TEST_LAST_NAME, user.getLastName());
        assertEquals(TEST_EMAIL, user.getEmail());
        assertEquals(TEST_PASSWORD, user.getPassWord());
    }

    @Test
    public void testIdManagement() {
        int testId = 42;
        user.setId(testId);
        assertEquals(testId, user.getId());
    }

    @Test
    public void testFirstNameUpdate() {
        String newName = "Jane";
        user.setFirstName(newName);
        assertEquals(newName, user.getFirstName());
    }

    @Test
    public void testLastNameUpdate() {
        String newName = "Smith";
        user.setLastName(newName);
        assertEquals(newName, user.getLastName());
    }

    @Test
    public void testEmailUpdate() {
        String newEmail = "jane.smith@example.com";
        user.setEmail(newEmail);
        assertEquals(newEmail, user.getEmail());
    }

    @Test
    public void testPasswordUpdate() {
        String newPassword = "newSecurePassword456";
        user.setPassWord(newPassword);
        assertEquals(newPassword, user.getPassWord());
    }
}