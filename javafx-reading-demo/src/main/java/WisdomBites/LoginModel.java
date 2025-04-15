package WisdomBites;
public class LoginModel {
    public static boolean checkCredentials(CharSequence username, CharSequence password) {

        return "Manny".contentEquals(username) && "123".contentEquals(password);
    }
}