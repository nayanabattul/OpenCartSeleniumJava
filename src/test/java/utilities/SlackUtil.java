package utilities;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SlackUtil {

    public static void sendMessage(String message) {

        String webhookUrl = System.getenv("SLACK_WEBHOOK_URL");

        if (webhookUrl == null || webhookUrl.isBlank()) {
            System.out.println(
                "SLACK_WEBHOOK_URL is not configured."
            );
            return;
        }

        String jsonPayload = "{"
                + "\"text\":\""
                + escapeJson(message)
                + "\""
                + "}";

        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(webhookUrl))
                    .header(
                        "Content-Type",
                        "application/json"
                    )
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            jsonPayload
                        )
                    )
                    .build();

            HttpResponse<String> response =
                    client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                "Slack response: "
                + response.statusCode()
                + " - "
                + response.body()
            );

        } catch (Exception e) {

            System.out.println(
                "Failed to send Slack notification: "
                + e.getMessage()
            );
        }
    }


    private static String escapeJson(String message) {

        return message
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}