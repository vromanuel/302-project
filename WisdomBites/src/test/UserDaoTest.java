import static org.junit.jupiter.api.Assertions.*;

import WisdomBites.model.DBConnection;
import WisdomBites.model.User;
import WisdomBites.model.UserDao;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class UserDaoTest {
    private UserDao userDao;
    private Connection connection;

    private final String TEST_FIRST_NAME = "Test";
    private final String TEST_LAST_NAME = "User";
    private final String TEST_EMAIL = "test.user@example.com";
    private final String TEST_PASSWORD = "testPassword123";
    private final String TEST_EMAIL_2 = "another.user@example.com";


    @BeforeEach
    public void setUp() throws Exception {
        // Create in-memory database connection
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // Initialize your UserDao with this connection
        userDao = new UserDao(null); // Pass null for HelloApplication if not needed

        // Create table
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS user (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "firstName VARCHAR NOT NULL," +
                    "lastName VARCHAR NOT NULL," +
                    "email VARCHAR NOT NULL UNIQUE," +
                    "passWord VARCHAR NOT NULL)");
        }
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testRegisterUserSuccess() {
        boolean result = UserDao.registerUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        assertTrue(result);
    }

    @Test
    public void testRegisterUserDuplicateEmail() {
        UserDao.registerUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        boolean result = UserDao.registerUser("Different", "Name", TEST_EMAIL, "differentPassword");
        assertFalse(result);
    }

    @Test
    public void testLoginSuccess() {
        UserDao.registerUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        User user = UserDao.login(TEST_EMAIL, TEST_PASSWORD);
        assertNotNull(user);
        assertEquals(TEST_FIRST_NAME, user.getFirstName());
        assertEquals(TEST_EMAIL, user.getEmail());
    }

    @Test
    public void testLoginWrongPassword() {
        UserDao.registerUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        User user = UserDao.login(TEST_EMAIL, "wrongPassword");
        assertNull(user);
    }

    @Test
    public void testLoginNonExistentUser() {
        User user = UserDao.login("nonexistent@example.com", "anyPassword");
        assertNull(user);
    }

    @Test
    public void testMultipleUserRegistration() {
        assertTrue(UserDao.registerUser(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD));
        assertTrue(UserDao.registerUser("Another", "User", TEST_EMAIL_2, "password456"));

        User user1 = UserDao.login(TEST_EMAIL, TEST_PASSWORD);
        User user2 = UserDao.login(TEST_EMAIL_2, "password456");

        assertNotNull(user1);
        assertNotNull(user2);
        assertNotEquals(user1.getId(), user2.getId());
    }

    @Test
    public void testCurrentUserManagement() {
        User testUser = new User(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        userDao.setCurrentUser(testUser);

        assertEquals(testUser, userDao.currentUser);
        assertEquals(TEST_FIRST_NAME, userDao.currentUser.getFirstName());
    }
}