package WisdomBites.controller;

import WisdomBites.model.User;

public class StateController {
    public static User currentUser;

    public static void setCurrentUser(User user) {currentUser = user;}
}
