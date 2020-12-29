package maksim.util;

import java.util.Objects;

public final class InputValidator {
    private InputValidator() {
        throw new UnsupportedOperationException("utility class");
    }

    public static void validate(String model, String phoneNumber, String color, int callsDuration) throws IllegalArgumentException {
        validateString(model);
        validateString(phoneNumber);
        validateString(color);
        validateInteger(callsDuration);
    }

    public static void validateInteger(int callsDuration) throws IllegalArgumentException {
        if (callsDuration < 0) {
            throw new IllegalArgumentException("Incorrect input: " + callsDuration);
        }
    }

    public static void validateString(String text) throws IllegalArgumentException {
        if (Objects.isNull(text) || text.trim().isEmpty() || text.length() < 3) {
            throw new IllegalArgumentException("Incorrect input: " + text);
        }
    }
}
