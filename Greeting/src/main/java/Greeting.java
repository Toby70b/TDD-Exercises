import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Greeting {

    public String greet(String[] names) {
        if (names == null) {
            return saySingleName("my friend");
        }

        List<String> sanitisedNamesList = sanitiseNamesList(names);

        List<String> lowerCaseNames = sanitisedNamesList.stream()
                .filter(name -> !name.equals(name.toUpperCase()))
                .collect(Collectors.toList());

        List<String> upperCaseNames = sanitisedNamesList.stream()
                .filter(name -> name.equals(name.toUpperCase()))
                .collect(Collectors.toList());

        boolean mixedGreeting = !(upperCaseNames.isEmpty() || lowerCaseNames.isEmpty());
        return mixedGreeting ? getLowerCaseGreeting(lowerCaseNames) + " AND " + getUpperCaseGreeting(upperCaseNames)
                : getLowerCaseGreeting(lowerCaseNames) + getUpperCaseGreeting(upperCaseNames);
    }

    private List<String> sanitiseNamesList(String[] namesArr) {
        Predicate<String> stringSurroundedByQuotes = (s -> s.startsWith("\"") && s.endsWith("\""));
        Predicate<String> stringIncludesCommas = (s -> s.contains(","));

        List<String> newNamesList = new ArrayList<>();
        for (String name : namesArr) {
            if (stringSurroundedByQuotes.test(name)) {
                newNamesList.add(stripQuotesOffString(name));
            } else if (stringIncludesCommas.test(name)) {
                newNamesList.addAll(splitNamesByComma(name));
            } else {
                newNamesList.add(name);
            }
        }
        return newNamesList;
    }

    private String getUpperCaseGreeting(List<String> upperCaseNames) {
        if (upperCaseNames.isEmpty()) {
            return "";
        } else if (upperCaseNames.size() == 1) {
            return shoutSingleName(upperCaseNames.get(0));
        }
        return buildUpperCaseGreeting(upperCaseNames);
    }

    private String buildUpperCaseGreeting(List<String> upperCaseNames) {
        StringBuilder stringBuilder = new StringBuilder("HELLO ");
        String lastName = upperCaseNames.remove(upperCaseNames.size() - 1);
        upperCaseNames.forEach(name -> stringBuilder.append(name).append(", "));
        return stringBuilder
                .append("AND ")
                .append(lastName)
                .append("!")
                .toString();
    }


    public String getLowerCaseGreeting(List<String> lowerCaseNames) {
        if (lowerCaseNames.isEmpty()) {
            return "";
        } else if (lowerCaseNames.size() == 1) {
            return saySingleName(lowerCaseNames.get(0));
        } else if (lowerCaseNames.size() == 2) {
            return "Hello, " + lowerCaseNames.get(0) + " and " + lowerCaseNames.get(1) + ".";
        }
        return buildLowerCaseGreeting(lowerCaseNames);
    }
    
    public String buildLowerCaseGreeting(List<String> lowerCaseNames) {
        StringBuilder stringBuilder = new StringBuilder("Hello, ");
        String lastName = lowerCaseNames.remove(lowerCaseNames.size() - 1);
        for (String name : lowerCaseNames) {
            stringBuilder.append(name)
                    .append(", ");
        }
        return stringBuilder
                .append("and ")
                .append(lastName)
                .append(".")
                .toString();
    }

    public String shoutSingleName(String name) {
        return "HELLO " + name + "!";
    }

    public String saySingleName(String name) {
        return "Hello, " + name + ".";
    }

    private String stripQuotesOffString(String name) {
        return name.substring(1, name.length() - 1);
    }

    private List<String> splitNamesByComma(String name) {
        return Arrays.asList(name.split(", "));
    }


}
