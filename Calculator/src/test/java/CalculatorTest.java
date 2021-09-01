import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("If no value is passed it should return zero")
    class NoValueCalculatorTests {

        @Test
        @DisplayName("an empty string should return zero")
        void emptyStringShouldReturnZero() {
            assertEquals(0, calculator.add(""));
        }

        @Test
        @DisplayName("a null parameter should return zero")
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
        @DisplayName("if -95 is passed it should return -95")
        void aSingleNumberShouldReturnItself19() {
            assertEquals(-95, calculator.add("-95"));
        }

        @Test
        @DisplayName("if 27000 is passed it should return 27000")
        void aSingleNumberShouldReturnItself27() {
            assertEquals(27000, calculator.add("27000"));
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
        @DisplayName("if -5 and 10 is passed it should return 5")
        void theSumOfMinusFiveAndTenShouldBeFive() {
            assertEquals(5, calculator.add("-5,10"));
        }

        @Test
        @DisplayName("if -10 and -6 is passed it should return -16")
        void theSumOfMinus10AndMinusSixShouldBeMinusSixteen() {
            assertEquals(-16, calculator.add("-10,-6"));
        }

        @Test
        @DisplayName("if 8, 12 and 4 are passed it should return 24")
        void theSumOfNumbersShouldEqualTwentyFour() {
            assertEquals(24, calculator.add("8,12,4"));
        }

        @Test
        @DisplayName("if 4, 4, 12 and -8 are passed it should return 12")
        void theSumOfNumbersShouldEqualTwelve() {
            assertEquals(12, calculator.add("4,4,12,-8"));
        }

        @Test
        @DisplayName("if 4, 16, -8, 17, -4, and -65 are passed it should return -40")
        void theSumOfNumbersShouldEqualMinusForty() {
            assertEquals(-40, calculator.add("4,16,-8,17,-4,-65"));
        }

    }

    //Assuming the value cant be a number
    @Nested
    @DisplayName("if a symbol line is provided before the numbers it should be treated as a delimiter")
    class CustomDelimiterCalculatorTests {
        @Test
        @DisplayName("if a symbol line is provided before the numbers it should be treated as a delimiter")
        void aStringWithASymbolProceedingTheNumbersShouldBeTreatedAsADelimiter() {
            assertEquals(3, calculator.add("//;\n1;2"));
        }

        @Test
        @DisplayName("if a symbol line is provided before the numbers it should be treated as a delimiter")
        void aCustomDelimiterShouldWorkWithRegularDelimiters() {
            assertEquals(35, calculator.add("//A\n16A8,7\n4"));
        }

    }

    @Test
    @DisplayName("if a newline is entered between the number it should be treated as a comma")
    void aStringWithANewLineShouldBeTreatedAsAComma() {
        assertEquals(6, calculator.add("1\n2,3"));
    }











}