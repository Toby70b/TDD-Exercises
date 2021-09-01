public class Calculator {

    public static final String DELIMITER_REGEX = "[,\n]";

    public int add(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }
        String[] values = s.split(DELIMITER_REGEX);
        try{
            return sumUpValues(values);
        }
        catch (NumberFormatException ex){
            //would log here if real app
            return -1;
        }
    }

    private int sumUpValues(String[] values) {
        int sum = 0;
        for (String value : values) {
            sum += Integer.parseInt(value);
        }
        return sum;
    }
}
