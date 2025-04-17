package WisdomBites.model;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao
{
    private Connection connection;

    public UserDao() {
        connection = DBConnection.getInstance();
        createUserTable();
    }

    private void createUserTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "userName VARCHAR NOT NULL UNIQUE,"
                    + "passWord VARCHAR NOT NULL"
                    + ")";

            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void registerUser() {

        String insertQuery = "INSERT INTO user (firstName, lastName, userName, passWord) VALUES (?,?,?,?)";
        try
        {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, "Manny");
            statement.setString(2, "Go");
            statement.setString(3, "manny");
            statement.setString(4, "123");
            statement.executeUpdate();

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static User login(String userNameSubmitted, String passWordSubmitted) {
        String query = "SELECT FROM user WHERE userName = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userNameSubmitted);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String passWord = resultSet.getString(5);
                User user = new User(firstName, lastName, userName, passWord);
                user.setId(id);
                return user;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
