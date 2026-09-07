package utilities;

public class ConfigReader {

    public static String getValidUsername() {
        return System.getenv("VALID_USERNAME");
    }

    public static String getValidPassword() {
        return System.getenv("VALID_PASSWORD");
    }

    public static String getInvalidUsername() {
        return System.getenv("INVALID_USERNAME");
    }

    public static String getInvalidPassword() {
        return System.getenv("INVALID_PASSWORD");
    }
}