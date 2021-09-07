import java.util.Arrays;
import java.util.List;

public class Calculator {

    public static final String DELIMITER_REGEX = "[,\n]";
    public static final List<String> REGEX_CHARACTERS = List.of(".", "^", "$", "*", "+", "-", "?", "(", ")", "[", "]", "{", "}", "\\", "|");

    public int add(String string) {
        String[] values;

        if (string == null || string.isEmpty()) {
            return 0;
        }

        if (string.startsWith("//")) {
            String customDelimiter = createCustomDelimeter(string);
            //remove preceding //
            string = string.substring(2);
            //Crappy fix in refactor
            string = string.replace("[","");
            string = string.replace("]","");
            values = string.split(DELIMITER_REGEX + "|" + customDelimiter);

        } else {
            values = string.split(DELIMITER_REGEX);
        }

        try {
            return sumUpValues(values);
        } catch (NumberFormatException ex) {
            //would log here if real app
            return -1;
        }
    }

    private String createCustomDelimeter(String string) {
        String customDelimiter = string.split("\n")[0].substring(2);

        if (multiLengthDelimeter(customDelimiter)) {
            customDelimiter = customDelimiter.substring(1, customDelimiter.length() - 1);
        }
        if (containsRegexCharacters(customDelimiter)) {
            customDelimiter =  escapeCustomDelimeter(customDelimiter);
        }
        return customDelimiter;
    }

    private boolean multiLengthDelimeter(String customDelimiter) {
        return customDelimiter.startsWith("[") && customDelimiter.endsWith("]");
    }

    private boolean containsRegexCharacters(String string) {
        return string.matches(".*[.^$*+\\-?()\\[\\]{}|].*");
    }

    private String escapeCustomDelimeter(String customDelimiter) {
        String escapedCustomDelimiter = customDelimiter;
        for (String regexCharacter : REGEX_CHARACTERS) {
            if (customDelimiter.contains(regexCharacter)) {
                escapedCustomDelimiter = escapedCustomDelimiter.replace(regexCharacter, "\\" + regexCharacter);
            }
        }
        return escapedCustomDelimiter;
    }

    private int sumUpValues(String[] values) {
        int sum = 0;
        for (String value : values) {
            int intValue = getIntValueFromString(value);
            if (intValue < 0) {
                throw new IllegalArgumentException(String.format("Negatives not allowed [%s]", buildExceptionMessage(values)));
            } else if (intValue <= 1000) {
                sum += intValue;
            }
        }
        return sum;
    }

    private String buildExceptionMessage(String[] values) {
        String negativeNumbersString = Arrays.stream(values)
                .filter(n -> Integer.parseInt(n) < 0)
                .reduce("", (totalString, value) -> totalString + value + ",");
        //remove trailing comma
        negativeNumbersString = negativeNumbersString.substring(0, negativeNumbersString.length() - 1);
        return negativeNumbersString;
    }

    private int getIntValueFromString(String value) {
        if (value.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value);
    }
}
