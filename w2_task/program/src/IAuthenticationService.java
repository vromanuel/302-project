package w2_task.program.src;

public interface IAuthenticationService {
    User signUp(String username, String password);
    User logIn(String username, String password);
}