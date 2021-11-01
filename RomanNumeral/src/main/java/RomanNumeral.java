import java.util.Map;

public class RomanNumeral {

    Map<Character, Integer> romanNumeralValueMap = Map.of('I', 1, 'V', 5,'X',
            10,'L',50,'C',100,'D',500,'M',1000);

    public int convertRomanNumeralsToNumbers(String romanNumeralString) {
        return calculateRomanNumeralSum(0,romanNumeralString);
    }

    public int calculateRomanNumeralSum(int index, String romanNumeralString) {
        if (romanNumeralString == null || romanNumeralString.isBlank()) {
            return 0;
        }
        char[] romanNumerals = romanNumeralString.toCharArray();
        if (index == romanNumeralString.length() - 1) {
            return romanNumeralValueMap.get(romanNumerals[index]);
        } else {
            boolean removeValueFromSum = romanNumeralValueMap.get(romanNumerals[index]) <
                    romanNumeralValueMap.get(romanNumerals[index + 1]);
            if (removeValueFromSum) {
                return calculateRomanNumeralSum(index+1, romanNumeralString) - romanNumeralValueMap.get(romanNumerals[index]);
            }
            return romanNumeralValueMap.get(romanNumerals[index]) + calculateRomanNumeralSum(++index, romanNumeralString);
        }
    }
}
