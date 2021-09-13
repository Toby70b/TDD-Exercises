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
    @DisplayName("If the name isn't fully capitalised it should return a normal greeting ")
    class normalGreetingTests {

        @Test
        @DisplayName("It should return a normal greeting with a lowercase name")
        void itShouldReturnANormalGreetingWithALowercaseName() {
            assertEquals("Hello, bob.", greeting.greet(new String[]{"bob"}));
        }

        @Test
        @DisplayName("It should return a normal greeting with a name that includes some captial letters")
        void itShouldReturnGreetingIncludingSomeCapitalLetters() {
            assertEquals("Hello, BoB.", greeting.greet(new String[]{"BoB"}));
        }

        @Test
        @DisplayName("It should return a normal greeting with a lowercase name including numbers and symbols")
        void itShouldReturnGreetingWithANameIncludingNumbersAndSymbols() {
            assertEquals("Hello, BoB124698328749*£*$)(\"$£.\".", greeting.greet(new String[]{"BoB124698328749*£*$)(\"$£.\""}));
        }

        @Test
        @DisplayName("If an array of two lowercase names is passed, return a normal greeting with both names")
        void itShouldReturnANormalGreetingWithTwoNames() {
            assertEquals("Hello, Jill and Jane.", greeting.greet(new String[]{"Jill", "Jane"}));
        }

        @Test
        @DisplayName("If multiple lowercase names are passed return a comma separated greeting with all names")
        void itShouldReturnANormalGreetingWithMultipleNames() {
            assertEquals("Hello, Amy, Brian, and Charlotte.", greeting.greet(new String[]{"Amy", "Brian", "Charlotte"}));
        }

    }

    @Nested
    @DisplayName("If the name is fully capitalised it should return a shout greeting ")
    class ShoutGreetingTests {
        @Test
        @DisplayName("It should return a shout greeting with a uppercase name")
        void itShouldReturnAShoutGreetingWithAnUppercaseName() {
            assertEquals("HELLO JERRY!", greeting.greet(new String[]{"JERRY"}));
        }

        @Test
        @DisplayName("It should return a shout greeting with a uppercase name containing numbers and symbols")
        void itShouldReturnAShoutGreetingIncludingNumbersAndSymbols() {
            assertEquals("HELLO JERRY124698328749*£*$)(\"$£.\"!", greeting.greet(new String[]{"JERRY124698328749*£*$)(\"$£.\""}));
        }

        @Test
        @DisplayName("It should return a shout greeting with two names separated with \"AND\"")
        void itShouldReturnAShoutGreetingWithTwoNames() {
            assertEquals("HELLO TOM AND JERRY!", greeting.greet(new String[]{"TOM", "JERRY"}));
        }

        @Test
        @DisplayName("It should return a shout greeting with multiple names separated with commas")
        void itShouldReturnAShoutGreetingWithMultipleNames() {
            assertEquals("HELLO TOM, JERRY, AND SPIKE!", greeting.greet(new String[]{"TOM", "JERRY", "SPIKE"}));
        }
    }


    @Nested
    @DisplayName("A Greeting can consist of a mix of both normal greetings and shout greetings")
    class MixedGreetingTests {
        @Test
        @DisplayName("Allow mixing of a single normal greeting and a single shout")
        void itShouldReturnAMixOfOneNormalAndOneShoutGreeting() {
            assertEquals("Hello, Amy. AND HELLO BRIAN!", greeting.greet(new String[]{"Amy", "BRIAN"}));
        }

        @Test
        @DisplayName("Allow mixing of a two normal greetings and two shouts")
        void itShouldReturnAMixOfTwoNormalAndTwoShoutGreeting() {
            assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN AND TOM!", greeting.greet(new String[]{"Amy", "BRIAN","Charlotte","TOM"}));
        }

        @Test
        @DisplayName("Allow mixing of a multiple normal greetings and multiple shouts")
        void itShouldReturnAMixOfMultipleNormalAndMultipleShoutGreeting() {
            assertEquals("Hello, Amy, Steve, Fred, and Charlotte. AND HELLO BRIAN, TOM, AND TONY!", greeting.greet(new String[]{"Amy", "BRIAN", "Steve","Fred","TOM", "Charlotte","TONY"}));
        }
    }

    @Nested
    @DisplayName("If any names contain an unescaped comma then the it should be split with the comma as the delimiter")
    class SplitNameGreetingTests {
        @Test
        @DisplayName("It should split normal greetings containing commas")
        void itShouldSplitNormalGreetingsByComma() {
            assertEquals("Hello, Bob, Charlie, and Dianne.", greeting.greet(new String[]{"Bob", "Charlie, Dianne"}));
        }

        @Test
        @DisplayName("It should split shout greetings containing containing commas")
        void itShouldSplitShoutGreetingsByComma() {
            assertEquals("HELLO TOM, JERRY, AND BOB!", greeting.greet(new String[]{"TOM, JERRY","BOB"}));
        }

        @Test
        @DisplayName("It should split strings containing  multiple commas")
        void itShouldSplitGreetingsWithMultipleCommas() {
            assertEquals("Hello, Bob, Charlie, and Dianne.", greeting.greet(new String[]{"Bob", "Charlie, Dianne"}));
        }

        @Test
        @DisplayName("It should not split strings which contain commas which are also surrounded by quotes")
        void itShouldNotSplitGreetingsWithEscapedCommas() {
            assertEquals("Hello, Bob, Tony, Steve, and Charlie, Dianne.", greeting.greet(new String[]{"Bob", "Tony, Steve", "\"Charlie, Dianne\""}));
        }

    }

    @Test
    @DisplayName("If no name is provided, it should return a greeting with a default value")
    void itShouldReturnADefaultValueIfInputIsNull() {
        assertEquals("Hello, my friend.", greeting.greet(null));
    }
}
