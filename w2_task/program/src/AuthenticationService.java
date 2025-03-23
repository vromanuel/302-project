package w2_task.program.src;

import java.util.ArrayList;


public class AuthenticationService implements IAuthenticationService
{
    private ArrayList<User> users = new ArrayList<>();

    public AuthenticationService()
    {
        User defaultUser = new User("test", "test");
        this.users.add(defaultUser);
    }

    public User signUp(String username, String password)
    {
        for (User user : this.users) {
            if (username.equals(user.getUsername())) {
                return null;
            }
        }

        User newUser = new User(username, password);
        this.users.add(newUser);
        return newUser;

    }

    public User logIn(String username, String password)
    {
        for (User user: this.users) {
            if (username.equals(user.getUsername())) {
             if(password.equals(user.getPassword()))
             {
                 return user;
             }
            }
        }
        return null;
    }


}
