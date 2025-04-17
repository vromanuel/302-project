package WisdomBites.model;

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

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}

    public String getPassWord() {return passWord;}
    public void setPassWord(String passWord) {this.passWord = passWord; }


}