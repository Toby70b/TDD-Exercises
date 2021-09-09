import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Calculator {

    public static final String DELIMITER_REGEX = "[,\n]";
    public static final List<String> REGEX_CHARACTERS = List.of(".", "^", "$", "*", "+", "-", "?", "(", ")", "[", "]", "{", "}", "\\", "|");

    public int add(String calculatorString) {
        String[] values;

        if (calculatorString == null || calculatorString.isEmpty()) {
            return 0;
        }

        if (calculatorString.startsWith("//")) {
            calculatorString = calculatorString.substring(2);
            boolean multipleCustomDelimiters = calculatorString.matches("\\[.+\n.*");
            String[] splitString = calculatorString.split("\n");
            List<String> customDelimiters;
            if(multipleCustomDelimiters) {
                customDelimiters =  processCustomDelimiters(customDelimiters = getCustomerDelimiters(splitString[0]));
                calculatorString = calculatorString.replace(splitString[0], "");
            }
            else {
                customDelimiters = processCustomDelimiters(Collections.singletonList(splitString[0]));
            }
            values = calculatorString.split(buildDelimiterRegex(customDelimiters));
        } else {
            values = calculatorString.split(DELIMITER_REGEX);
        }

        try {
            return sumUpValues(values);
        } catch (NumberFormatException ex) {
            //would log here if real app
            return -1;
        }
    }

    private List<String> processCustomDelimiters(List<String> customDelimiterList) {
        List<String> escapedCustomDelimiters = new ArrayList<>();
        for (String customDelimiter : customDelimiterList) {
            if (containsRegexCharacters(customDelimiter)) {
                escapedCustomDelimiters.add(escapeCustomDelimeter(customDelimiter));
            }
            else {
                escapedCustomDelimiters.add(customDelimiter);
            }
        }
        return escapedCustomDelimiters;
    }

    private String buildDelimiterRegex(List<String> escapeCustomDelimiters) {
        StringBuilder delimiterRegex = new StringBuilder(DELIMITER_REGEX + "|");
        for (String string:escapeCustomDelimiters) {
            delimiterRegex.append(string);
            delimiterRegex.append("|");
        }
        return delimiterRegex.substring(0, delimiterRegex.length()-1);
    }

    private List<String> getCustomerDelimiters(String delimiterLine) {
        List<String> customDelimiters = new ArrayList<>();
        char[] chars = delimiterLine.toCharArray();
        StringBuilder customDelimiter = new StringBuilder();
        for (char aChar : chars) {
            switch (aChar) {
                case '[':
                    break;
                case ']':
                    customDelimiters.add(customDelimiter.toString());
                    customDelimiter = new StringBuilder();
                    break;
                default:
                    customDelimiter.append(aChar);
            }
        }

        return customDelimiters;
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
