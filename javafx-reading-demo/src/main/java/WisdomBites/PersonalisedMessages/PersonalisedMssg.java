package main.java.WisdomBites.PersonalisedMessages;

import java.time.LocalTime;

public class PersonalisedMssg {

    public static String generateWelcomeMessage(String name, String subject, int streakDays) {
        String greeting = getGreeting();
        String streakMsg = getStreakMessage(streakDays);
        return String.format("%s %s! You're doing great in %s. %s", greeting, name, subject, streakMsg);
    }

    public static String generatePrediction(String subject, int weeksStudied) {
        if (weeksStudied >= 6) {
            return "You're nearly an expert in " + subject + "! Keep it up!!";
        } else if (weeksStudied >= 3) {
            return "Based on your progress in " + subject + ", you’ll be an expert in 2 weeks!";
        } else {
            return "Every step counts in " + subject + ". You're on your way!";
        }
    }

    private static String getGreeting() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) return "Good morning";
        if (now.isBefore(LocalTime.of(17, 0))) return "Good afternoon";
        return "Good evening";
    }

    private static String getStreakMessage(int days) {
        if (days >= 7) return "You're on fire! Amazing streak!";
        if (days >= 3) return "You're on a solid streak — great job!";
        if (days > 0) return "You're warming up — keep it going!";
        return "Let’s start your streak today!";
    }
}
