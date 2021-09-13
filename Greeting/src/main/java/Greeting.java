import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class containing the ability to greet users by name
 */
public class Greeting {

    /**
     * returns a greeting string containing the names passed in the parameter, in the form "Hello,
     * {@literal <}name{@literal >}."<br/><br/>
     * <p>
     * If null is passed it will return a default greeting "Hello, my friend"<br/><br/>
     * <p>
     * If multiple values are passed it will return each seperated with an oxford comma.
     * The final name will also be prefixed with "and". For example "Hello, Billy, Tony, and Emily"<br/><br/>
     * <p>
     * If a name is fully capitalised then a shout greeting will be returned instead. A shout is
     * of the format "HELLO {@literal <}name{@literal >}! <br/><br/>
     * <p>
     * Capitalised and non-capitalised names can be used in the same array. This will be treated as a "mixed" greeting.
     * Mixed Greetings will first greet the non-capitalised names before "shouting" the capitalised names. For example
     * "Hello, Amy and Charlotte. AND HELLO BRIAN AND TOM!"<br/><br/>
     * <p>
     * If a name contains a comma the name will be split with the comma acting as the delimiter<br/><br/>
     * <p>
     * Strings surrounded in escaped double quotes escaped will be treated as a single name regardless of commas
     *
     * @param names an array of string representing the names to be greeted
     * @return a String greeting formatted with the rules declared above
     */
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

    /**
     * Strips quotes and splits commas of strings so they dont appear in the greeting
     *
     * @param namesArr an array of strings to be sanitised
     * @return an array of strings split by comma (unless escaped) and with quotes removed
     */
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

    /**
     * Returns a shout greeting depending on how many names are required to be shouted
     *
     * @param upperCaseNames an List of uppercase strings to shout
     * @return a shout greeting containing the names from the List parameter
     */
    private String getUpperCaseGreeting(List<String> upperCaseNames) {
        if (upperCaseNames.isEmpty()) {
            return "";
        } else if (upperCaseNames.size() == 1) {
            return shoutSingleName(upperCaseNames.get(0));
        } else if (upperCaseNames.size() == 2) {
            return shoutTwoNames(upperCaseNames);
        }
        return buildShoutGreeting(upperCaseNames);
    }

    /**
     * Returns a shout greeting for when only one name is used
     *
     * @param name the name to shout
     * @return a shout greeting
     */
    public String shoutSingleName(String name) {
        return "HELLO " + name + "!";
    }

    /**
     * Creates an shout greeting for two names
     *
     * @param upperCaseNames an array of uppercase strings to be included in the greeting
     * @return a String representing the shout greeting
     */
    private String shoutTwoNames(List<String> upperCaseNames) {
        return "HELLO " + upperCaseNames.get(0) + " AND " + upperCaseNames.get(1) + "!";
    }

    /**
     * Builds an shout greeting for more multiple names
     *
     * @param upperCaseNames an array of uppercase strings to be included in the greeting
     * @return a String representing the shout greeting
     */
    private String buildShoutGreeting(List<String> upperCaseNames) {
        StringBuilder stringBuilder = new StringBuilder("HELLO ");
        String lastName = upperCaseNames.remove(upperCaseNames.size() - 1);
        upperCaseNames.forEach(name -> stringBuilder.append(name).append(", "));
        return stringBuilder
                .append("AND ")
                .append(lastName)
                .append("!")
                .toString();
    }

    /**
     * Returns a normal greeting depending on how many names are required to be greeted
     *
     * @param lowerCaseNames an List of strings to greet
     * @return a normal greeting containing the names from the List parameter
     */
    public String getLowerCaseGreeting(List<String> lowerCaseNames) {
        if (lowerCaseNames.isEmpty()) {
            return "";
        } else if (lowerCaseNames.size() == 1) {
            return saySingleName(lowerCaseNames.get(0));
        } else if (lowerCaseNames.size() == 2) {
            return sayTwoNames(lowerCaseNames, "Hello, ", " and ", ".");
        }
        return buildLowerCaseGreeting(lowerCaseNames);
    }


    /**
     * Returns a normal greeting for when only one name is used
     *
     * @param name the name to greet
     * @return a greeting
     */
    public String saySingleName(String name) {
        return "Hello, " + name + ".";
    }

    /**
     * Returns a normal greeting with two names
     *
     * @param lowerCaseNames an List of strings to greet
     * @return a normal greeting containing the names from the List parameter
     */
    public String sayTwoNames(List<String> lowerCaseNames, String s, String s2, String s3) {
        return s + lowerCaseNames.get(0) + s2 + lowerCaseNames.get(1) + s3;
    }

    /**
     * Builds an normal greeting for more multiple names
     *
     * @param lowerCaseNames an array of strings to be included in the greeting
     * @return a String representing the greeting
     */
    public String buildLowerCaseGreeting(List<String> lowerCaseNames) {
        StringBuilder stringBuilder = new StringBuilder("Hello, ");
        String lastName = lowerCaseNames.remove(lowerCaseNames.size() - 1);
        lowerCaseNames.forEach(name -> stringBuilder.append(name)
                .append(", "));
        return stringBuilder
                .append("and ")
                .append(lastName)
                .append(".")
                .toString();
    }

    /**
     * Removes first and last character from strip, should be used to remove quotes from escaped names
     *
     * @param name the name with quotes
     * @return the name with quotes removed
     */
    private String stripQuotesOffString(String name) {
        return name.substring(1, name.length() - 1);
    }

    /**
     * Splits a string by comma
     *
     * @param name the name with the comma
     * @return a list of strings split by the comma
     */
    private List<String> splitNamesByComma(String name) {
        return Arrays.asList(name.split(", "));
    }


}
