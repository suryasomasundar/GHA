package utils;

public class BSConfigHelper {

    public static String getUsername() {
        return System.getProperty("BROWSERSTACK_USERNAME", System.getenv("BROWSERSTACK_USERNAME"));
    }

    public static String getAccessKey() {
        return System.getProperty("BROWSERSTACK_ACCESS_KEY", System.getenv("BROWSERSTACK_ACCESS_KEY"));
    }

    public static void validateCreds(String username, String accessKey) {
        if (username == null || username.trim().isEmpty() ||
                accessKey == null || accessKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Please set BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY.");
        }
    }

    public static String getHubUrl(String username, String accessKey) {
        return "http://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";
    }
}
