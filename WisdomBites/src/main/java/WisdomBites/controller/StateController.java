package WisdomBites.controller;

import WisdomBites.model.User;

public class StateController {
    // The user that is logged in
    public static User currentUser;

    public static void setCurrentUser(User user) {currentUser = user;}

    public static int getUserId() {
        return currentUser != null ? currentUser.getId() : -1; // or throw an error if preferred
    }
}
