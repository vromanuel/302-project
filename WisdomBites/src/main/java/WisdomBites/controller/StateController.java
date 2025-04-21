package WisdomBites.controller;

import WisdomBites.model.User;

public class StateController {
    // This class stores the states


    // The user that is logged in
    public static User currentUser;

    public static void setCurrentUser(User user) {currentUser = user;}
}
