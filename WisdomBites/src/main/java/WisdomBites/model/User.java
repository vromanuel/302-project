package WisdomBites.model;

// Use r model
public class User
{
    // Establish the attributes of the User class
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String passWord;

    // Create a Constructor for the User class
    // Inputs:
    //      firstName: String,
    //      lastName: String,
    //      email: String,
    //      passWord: String
    public User(String firstName, String lastName, String email, String passWord) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    // get and set for passWord
    public String getPassWord() {return passWord;}
    public void setPassWord(String passWord) {this.passWord = passWord; }


}