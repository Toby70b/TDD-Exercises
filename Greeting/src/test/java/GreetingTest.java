import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingTest {
    Greeting greeting;

    @BeforeEach
    void setUp() {
        greeting = new Greeting();
    }

    @Nested
    @DisplayName("It should greet individuals correctly")
    class SingleNameGreetingTests {


        @Test
        @DisplayName("It should return a greeting with name")
        void returnGreetingWithName() {
            assertEquals("Hello, Bob.", greeting.greet(new String[]{"Bob"}));
        }

        @Test
        @DisplayName("If no name is provided it should return a greeting with a default value")
        void returnGreetingWithDefaultValue() {
            assertEquals("Hello, my friend.", greeting.greet(null));
        }

        @Test
        @DisplayName("If name is uppercase return a shout greeting")
        void returnShoutGreetingIfNameIsUppercase() {
            assertEquals("HELLO JERRY!", greeting.greet(new String[]{"JERRY"}));
        }

    }

    @Nested
    @DisplayName("It should greet multiple names correctly")
    class MultipleNameGreetingTests {

        @Test
        @DisplayName("If array of two names is passed, return a greeting with both names")
        void returnGreetingWithTwoNames() {
            assertEquals("Hello, Jill and Jane.", greeting.greet(new String[]{"Jill", "Jane"}));
        }

        @Test
        @DisplayName("If multiple names are passed return a comma separated greeting with all names")
        void returnGreetingWithMultipleNames() {
            assertEquals("Hello, Amy, Brian, and Charlotte.", greeting.greet(new String[]{"Amy", "Brian", "Charlotte"}));
        }

        @Test
        @DisplayName("Allow mixing of greetings and shouts")
        void returnMixedGreetingAndShout() {
            assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", greeting.greet(new String[]{"Amy", "BRIAN", "Charlotte"}));
        }

        @Test
        @DisplayName("Allow mixing of greetings and shouts with two shouts")
        void returnMixedGreetingAndShoutWithMultipleShouts() {
            assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN, AND TOM!", greeting.greet(new String[]{"Amy", "BRIAN", "TOM", "Charlotte"}));
        }

    }

    @Nested
    @DisplayName("It should split commas in names correctly")
    class SplitNameGreetingTests {
        @Test
        @DisplayName("It should split strings containing commas")
        void splitGreetingsByCommas() {
            assertEquals("Hello, Bob, Charlie, and Dianne.", greeting.greet(new String[]{"Bob", "Charlie, Dianne"}));
        }

        @Test
        @DisplayName("It should not split strings which contain commas which are also surrounded by quotes")
        void dontSplitGreetingsSurroundedByQuotes() {
            assertEquals("Hello, Bob and Charlie, Dianne.", greeting.greet(new String[]{"Bob", "\"Charlie, Dianne\""}));
        }
    }
}
