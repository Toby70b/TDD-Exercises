import java.util.Arrays;

public class Calculator {

    public static final String DELIMITER_REGEX = "[,\n]";

    public int add(String string) {
        String[] values;

        if (string == null || string.isEmpty()) {
            return 0;
        }

        if (string.startsWith("//")) {
            char customDelimiter = string.charAt(2);
            string = string.substring(2);
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

    private int sumUpValues(String[] values) {
        int sum = 0;
        for (String value : values) {
            int intValue = getIntValueFromString(value);
            if (intValue < 0) {
                throw new IllegalArgumentException(String.format("Negatives not allowed [%s]", buildExceptionMessage(values)));
            }
            sum += intValue;
        }
        return sum;
    }

    private String buildExceptionMessage(String[] values) {
        String negativeNumbersString = Arrays.stream(values)
                .filter(n -> Integer.parseInt(n) < 0)
                .reduce("", (totalString, value) -> totalString + value +",");
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
