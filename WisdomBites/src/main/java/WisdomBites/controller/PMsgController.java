package WisdomBites.controller;

import java.time.LocalTime;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class PMsgController {
    private static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";
    private static final String AI_MODEL = "llama3"; // or "mistral", "gemma", etc.
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static String generateWelcomeMessage(String name, String subject, int streakDays) {
        String greeting = getGreeting();
        String streakMsg = getStreakMessage(streakDays);

        String prompt = String.format(
                "Create a personalized welcome message for %s who is learning %s. " +
                        "They have a %d-day streak. Current time is %s. " +
                        "Make it motivational, warm, and concise (1 sentence max). " +
                        "Include their name and reference their progress.",
                name, subject, streakDays, greeting.toLowerCase()
        );

        return getAIResponse(prompt,
                String.format("%s %s! You're doing great in %s. %s", greeting, name, subject, streakMsg)
        );
    }


    public static String generatePrediction(String name, String subject, int weeksStudied, int weeksLeft, List<String> topicsLeft) {
        String topicsStr = topicsLeft.isEmpty()
                ? "No specific topics left."
                : String.join(", ", topicsLeft);

        String prompt = String.format(
                "Create a short and personalized learning prediction for a student studying %s. " +
                        "They have completed %d out of 13 weeks and have %d weeks left. " +
                        "Topics still to cover include: %s. " +
                        "Speak in second person (using 'you'), give encouraging feedback on progress and practical motivation to finish the rest. " +
                        "Do not mention their name. Limit the response to 3 sentences. " +
                        "Be naturally supportive and clear, with no quotes or generic fluff.",
                subject, weeksStudied, weeksLeft, topicsStr
        );

        String fallback;
        if (weeksStudied >= 10)
            fallback = "You're nearly done with " + subject + "! Keep the momentum and tackle the final topics confidently.";
        else if (weeksStudied >= 6)
            fallback = "You're past the halfway mark! Stay focused and work through the rest of the syllabus one step at a time.";
        else if (weeksStudied >= 3)
            fallback = "You've made a solid start in " + subject + ". Keep a steady pace and you’ll master the material.";
        else
            fallback = "Every bit of effort counts. Start with the upcoming topics and build your progress week by week.";

        return getAIResponse(prompt, fallback);
    }


    private static String getGreeting() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) return "Good morning";
        if (now.isBefore(LocalTime.of(17, 0))) return "Good afternoon";
        return "Good evening";
    }

    private static String getStreakMessage(int days) {
        if (days >= 7) return "You're on fire with your learning streak!";
        if (days >= 3) return "Great consistency with your studies!";
        if (days > 0) return "Keep building your learning habit!";
        return "Let's start an amazing learning streak today!";
    }

    public static String getAIResponse(String prompt, String fallback) {
        try {
            String requestBody = String.format(
                    "{\"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false, \"options\": {\"temperature\": 0.7}}",
                    AI_MODEL, escapeJson(prompt)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OLLAMA_API_URL))
                    .header("Content-Type", "application/json")
                    .timeout(java.time.Duration.ofSeconds(30))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("Raw response: " + response.body());

            String jsonResponse = response.body();

            // Match the full "response" value even with embedded quotes
            int responseIndex = jsonResponse.indexOf("\"response\":\"");
            if (responseIndex != -1) {
                int start = responseIndex + 11;
                int end = jsonResponse.indexOf("\",\"done\":", start);
                if (end > start) {
                    String aiResponse = jsonResponse.substring(start, end)
                            .replace("\\n", "\n")
                            .replace("\\\"", "\"")
                            .trim();

                    System.out.println("Final cleaned AI response:\n" + aiResponse);

                    if (!aiResponse.isEmpty()) {
                        return aiResponse;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("AI Service Error: " + e.getMessage());
            // Uncomment for debugging:
            e.printStackTrace();
        }
        return fallback;
    }

    private static String escapeJson(String input) {
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
