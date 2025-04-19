package WisdomBites.model;

// Use r model
public class User
{
    // Establish the attributes of the User class
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;

    // Create a Constructor for the User class
    // Inputs:
    //      firstName: String,
    //      lastName: String,
    //      userName: String,
    //      passWord: String
    public User(String firstName, String lastName, String userName, String passWord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passWord = passWord;
    }

    // get and set methods for the id attribute, the identifier for the users
    public int getId() {return id;}
    public void setId(int id) {
        this.id = id;
    }

    // get and set methods for firstName
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    // get and set for lastName
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    // get and set for userName
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    // get and set for passWord
    public String getPassWord() {return passWord;}
    public void setPassWord(String passWord) {this.passWord = passWord; }


}