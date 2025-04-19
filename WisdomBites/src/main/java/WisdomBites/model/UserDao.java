package WisdomBites.model;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class UserDao
{
    // Connection to the database
    private static Connection connection;

    // Initialise the User Data Access object
    public UserDao() {
        // Gets the instance of the database connection
        connection = DBConnection.getInstance();
        createUserTable();
    }

    private void createUserTable() {
        // Creates a table to store user data if the table does not exist
        try {
            // Create a statement with an SQL query
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL UNIQUE,"
                    + "passWord VARCHAR NOT NULL"
                    + ")";

            // Execute the statement with the query
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean registerUser(String firstName, String lastName, String email, String passWord) {
        // Adds a new user to the table of users

        try
        {

            // SQL to insert the user's firstName, lastName, email, and passWord
            String insertQuery = "INSERT INTO user (firstName, lastName, email, passWord) VALUES (?,?,?,?)";

            // Creates a new PreparedStatement
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            // Add the values to be inserted into the table into the query where appropriate
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, passWord);

            statement.executeUpdate();

        } catch(Exception e)
        {
            // Return the exception message
            return false;
        }
        return true;
    }

    public static User login(String emailSubmitted, String passWordSubmitted) {
        // Authenticate the email and password for the user, check if the password entered matches that of
        // the password stored in the database

        try{
            // SQL code to retrieve the email entered by the user
            String query = "SELECT * FROM user WHERE email = ?";

            // Create a new PreparedStatement
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, emailSubmitted);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String passWord = resultSet.getString(5);
                User loggedInUser = new User(firstName, lastName, email, passWord);
                loggedInUser.setId(id);

                if (Objects.equals(passWordSubmitted, passWord))
                return loggedInUser;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
