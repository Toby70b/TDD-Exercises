import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralTest {
    RomanNumeral romanNumeral;

    @BeforeEach
    void setUp() {
        romanNumeral = new RomanNumeral();
    }

    @Test
    @DisplayName("If the string is empty then it should return 0")
    void ifStringIsEmptyItShouldReturnZero() {
        assertEquals(0,romanNumeral.convertRomanNumeralsToNumbers(""),0);
    }

    @Test
    @DisplayName("If the string is null then it should return 0")
    void ifStringIsNullItShouldReturnZero() {
        assertEquals(0,romanNumeral.convertRomanNumeralsToNumbers(null),0);
    }

    @Test
    @DisplayName("It convert the character \"I\" into 1")
    void itShouldConvertIintoOne() {
        assertEquals(1,romanNumeral.convertRomanNumeralsToNumbers("I"),1);
    }

    @Test
    @DisplayName("It should add multiple \"I\" characters together")
    void itShouldBeAbleToHandleMultipleICharacters() {
        assertEquals(3,romanNumeral.convertRomanNumeralsToNumbers("III"),3);
    }

    @Test
    @DisplayName("It should convert the character \"V\" into 5")
    void itShouldConvertVIntoFive() {
        assertEquals(5,romanNumeral.convertRomanNumeralsToNumbers("V"),5);
    }

    @Test
    @DisplayName("It should convert \"VII\" into 7")
    void itShouldConvertVIIInto7() {
        assertEquals(7,romanNumeral.convertRomanNumeralsToNumbers("VII"));
    }


    @Test
    @DisplayName("It should convert \"IV\" into 4")
    void itShouldConvertIVIntoFour() {
        assertEquals(4,romanNumeral.convertRomanNumeralsToNumbers("IV"));
    }

    @Test
    @DisplayName("It should convert \"X\" into 10")
    void itShouldConvertXIntoTen() {
        assertEquals(10,romanNumeral.convertRomanNumeralsToNumbers("X"));
    }

    @Test
    @DisplayName("It should convert \"XXXIV\" into 34")
    void itShouldConvertXXXIVIntoThirtyFour() {
        assertEquals(34,romanNumeral.convertRomanNumeralsToNumbers("XXXIV"));
    }

    @Test
    @DisplayName("It should convert \"L\" into 50")
    void itShouldConvertLIntoFifty() {
        assertEquals(50,romanNumeral.convertRomanNumeralsToNumbers("L"));
    }

    @Test
    @DisplayName("It should convert \"C\" into 100")
    void itShouldConvertLIntoOneHundred() {
        assertEquals(100,romanNumeral.convertRomanNumeralsToNumbers("C"));
    }

    @Test
    @DisplayName("It should convert \"D\" into 500")
    void itShouldConvertDIntoFiveHundred() {
        assertEquals(500,romanNumeral.convertRomanNumeralsToNumbers("D"));
    }

    @Test
    @DisplayName("It should convert \"M\" into 1000")
    void itShouldConvertMIntoOneThousand() {
        assertEquals(1000,romanNumeral.convertRomanNumeralsToNumbers("M"));
    }

    @Test
    @DisplayName("It should convert \"MMXXII\" into 2022")
    void itShouldConvertMMXXIIIntoTwoThousandAndTwo() {
        assertEquals(2022,romanNumeral.convertRomanNumeralsToNumbers("MMXXII"));
    }

    @Test
    @DisplayName("It should convert \"MCMXLIV\" into 1944")
    void itShouldConvertMCMXLIVIntoOneThousandNineHundredAndFortyFour() {
        assertEquals(1944,romanNumeral.convertRomanNumeralsToNumbers("MCMXLIV"));
    }



}