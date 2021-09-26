import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("If no value is passed as a parameter it should return zero")
    class NoValueCalculatorTests {

        @Test
        @DisplayName("an empty string  should return zero")
        void emptyStringShouldReturnZero() {
            assertEquals(0, calculator.add(""));
        }

        @Test
        @DisplayName("null should return zero")
        void nullShouldReturnZero() {
            assertEquals(0, calculator.add(null));
        }
    }

    @Nested
    @DisplayName("If a single integer value is passed it should return that value")
    class SingleValueCalculatorTests {
        @Test
        @DisplayName("if 4 is passed it should return 4")
        void aSingleNumberShouldReturnItself4() {
            assertEquals(4, calculator.add("4"));
        }

        @Test
        @DisplayName("if 999 is passed it should return 999")
        void aSingleNumberShouldReturnItself999() {
            assertEquals(999, calculator.add("999"));
        }

        @Test
        @DisplayName("if 0 is passed it should return 0")
        void aSingleNumberShouldReturnItself0() {
            assertEquals(0, calculator.add("0"));
        }
    }

    //Not a requirement on the page, however, in a real-world app you would need to take this scenario into account
    @Nested
    @DisplayName("If a non string value is encountered it should return -1")
    class NonStringValueCalculatorTests {
        @Test
        @DisplayName("if the string value \"a\" is passed it should return -1")
        void aStringValueShouldReturnMinusOne() {
            assertEquals(-1, calculator.add("a"));
        }

        @Test
        @DisplayName("if a string value is passed after an integer it should return -1")
        void aMixedStringIntegerValueShouldReturnMinusOne() {
            assertEquals(-1, calculator.add("4,a"));
        }
    }

    @Nested
    @DisplayName("if more than one value is passed it should return the sum of all values")
    class MultipleValueCalculatorTests {
        @Test
        @DisplayName("if 2 and 5 is passed it should return 7")
        void theSumOfTwoAndFiveShouldBeSeven() {
            assertEquals(7, calculator.add("2,5"));
        }

        @Test
        @DisplayName("if 8, 12 and 4 are passed it should return 24")
        void theSumOfNumbersShouldEqualTwentyFour() {
            assertEquals(24, calculator.add("8,12,4"));
        }

    }

    //Assuming the value cant be a number
    //Also if "-" is passed as a symbol any negative numbers passed would be treated as positive
    @Nested
    @DisplayName("if a symbol is provided on a line before the numbers it should be treated as a delimiter")
    class CustomDelimiterCalculatorTests {
        @Test
        @DisplayName("if a ; symbol is provided before the numbers and 1 and 2 are passed as the values it should return 3")
        void aStringWithASymbolProceedingTheNumbersShouldBeTreatedAsADelimiter() {
            assertEquals(3, calculator.add("//;\n1;2"));
        }

        @Test
        @DisplayName("if a custom delimiter is provided it should still work with regular delimeters, the sum should return 35")
        void aCustomDelimiterShouldWorkWithRegularDelimiters() {
            assertEquals(35, calculator.add("//A\n16A8,7\n4"));
        }

        @Test
        @DisplayName("if a *** symbol is provided before the numbers and 1 and 2 are passed as the values it should return 3")
        void aStringWithADelimiterLongerThanOneCharacterShouldBeTreatedAsADelimiter() {
            assertEquals(6, calculator.add("//[***]\n1***2***3"));
        }

        @Test
        @DisplayName("if [] symbol is provided before the numbers and 1,2 and 3 are passed as the values it should return 6")
        void ifSquareBracketsAreUsedAsDelimitersItShouldReturnSumAsExpected() {
            assertEquals(6, calculator.add("//[]\n1[]2[]3"));
        }

        @Test
        @DisplayName("Inner square brackets should be treated as part of the delimiter")
        void innerSquareBracketsShouldBeTreatedAsPartOfTheDelimiter() {
            assertEquals(6, calculator.add("//[[***]]\n1[***]2[***]3"));
        }
    }

    @Nested
    @DisplayName("if a regex symbol is provided on a line before the numbers it should be treated as a delimiter")
    class CustomDelimiterRegexValueCalculatorTests {
        @Test
        @DisplayName("if a ? symbol is provided before the numbers and 1, 2 and 4 are passed as the values it should return 7")
        void aStringWithAQuestionMarkDelimiterShouldBeTreatedAsADelimiter() {
            assertEquals(7, calculator.add("//?\n1?2?4"));
        }

        @Test
        @DisplayName("if a * symbol is provided before the numbers and 1, 2 and 5 are passed as the values it should return 8")
        void aStringWithAAsteriskShouldBeTreatedAsADelimiter() {
            assertEquals(8, calculator.add("//*\n1*2*5"));
        }

        @Test
        @DisplayName("if a $ symbol is provided before the numbers and 1, 2 and 5 are passed as the values it should return 8")
        void aStringWithADollarShouldBeTreatedAsADelimiter() {
            assertEquals(8, calculator.add("//*\n1*2*5"));
        }

        @Test
        @DisplayName("if a [ and symbol is provided before the numbers and 8,9 and 10 are passed as the values it should return 27")
        void aStringWithAnOpeningSquareBracketShouldBeTreatedAsADelimiter() {
            assertEquals(27, calculator.add("//[\n8[9,10"));
        }
        ///.... and continuing on...

    }


    @Test
    @DisplayName("if a newline is entered between the number it should be treated as a delimiter")
    void aStringWithANewLineShouldBeTreatedAsAComma() {
        assertEquals(6, calculator.add("1\n2,3"));
    }


    @Nested
    @DisplayName("if a negative value is provided anywhere in the sum it should throw an exception")
    class NegativeValueCalculatorTests {
        @Test
        @DisplayName("if the value -1 exists in the string it should throw an exception")
        void aStringWithANegativeNumberShouldThrowException() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                calculator.add("4,15,27,-1,17");
            });
            String expectedMessage = "Negatives not allowed [-1]";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        @DisplayName("if multiple negative values exist in the string it should return a list of all of them")
        void aStringWithASymbolProceedingTheNumbersShouldBeTreatedAsADelimiter() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("4,15,27,-1,17,-4,0,-78"));
            String expectedMessage = "Negatives not allowed [-1,-4,-78]";
            String actualMessage = exception.getMessage();
            assertEquals(expectedMessage,actualMessage);
        }
    }

    @Nested
    @DisplayName("if value greater than 1000 is included it should be ignored")
    class valuesGreaterThanOneThousandCalculatorTests {
        @Test
        @DisplayName("if 1001 and 2 are passed it should return 2")
        void valuesGreaterThanOneThousandShouldBeIgnored() {
            assertEquals(2, calculator.add("1001,2"));
        }
        @Test
        @DisplayName("if 1000 and 2 are passed it should return 10002")
        void valuesLessThanOrEqualToOneThousandShouldNotBeIgnored() {
            assertEquals(1002, calculator.add("1000,2"));
        }
    }


    @Nested
    @DisplayName("Multiple custom delimiters enclosed within square brackets should be supported")
    class MultipleCustomDelimiterCalculatorTests {
        @Test
        @DisplayName("if a * and % symbol is provided before the numbers and 1,2 and 3 are passed as the values it should return 6")
        void aStringWithMultipleDelimitersShouldReturnSumAsExpected() {
            assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        }
        @DisplayName("if a * and % symbol is provided before the numbers and 1,2 and 3 are passed as the values it should return 6")
        void aStringWithMultipleDelimitersOfVaryingLengthShouldReturnAsExpected() {
            assertEquals(15, calculator.add("//[***][%%%%%%]\n1,2%%%%%%3%%%%%%5***4"));
        }
    }
}