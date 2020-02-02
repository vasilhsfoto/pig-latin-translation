package com.citrix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PigLatinTranslationServiceTest {
    private PigLatinTranslationService service = new PigLatinTranslationService();

    /**
     * Test cases from the assignment description
     */
    @Test
    void translate_givenWordStartsWithConsonant_shouldReturnCorrectMapping() {
        String text = "Hello";
        String expectedText = "Ellohay";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordStartsWithVowel_shouldReturnCorrectMapping() {
        String text = "apple";
        String expectedText = "appleway";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordEndsInWay_shouldNotBeModified() {
        String text = "stairway";
        assertThat(service.translate(text)).isEqualTo(text);
    }

    @Test
    void translate_givenWordStartsWithConsonantWithPunctuation_shouldReturnCorrectMapping() {
        String text = "can't";
        String expectedText = "antca'y";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordStartsWithVowelWithPunctuation_shouldReturnCorrectMapping() {
        String text = "end.";
        String expectedText = "endway.";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordWithHyphen_shouldReturn2WordsTranslatedAndKeepTheHyphen() {
        String text = "this-thing";
        String expectedText = "histay-hingtay";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordStartsWithConsonantAndCapitalLetterAtTheStart_shouldReturnCorrectMapping() {
        String text = "Beach";
        String expectedText = "Eachbay";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordStartsWithConsonantAnd2LettersCapital_shouldReturnCorrectMapping() {
        String text = "McCloud";
        String expectedText = "CcLoudmay";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    /**
     * Test cases with breaking words
     */
    @Test
    void translate_givenSentence_shouldReturnTranslationAndKeepTheOriginalFormat() {
        String text = "Lorem ipsum\tdolor sit amet.";
        String expectedText = "Oremlay ipsumway\tolorday itsay ametway.";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenParagraph_shouldReturnTranslationAndKeepTheOriginalFormat() {
        String text = "Lorem ipsum\tdolor sit amet.\nDolor\n";
        String expectedText = "Oremlay ipsumway\tolorday itsay ametway.\nOlorday\n";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordWith3Hyphens_shouldReturn3WordsTranslatedAndKeepTheHyphens() {
        String text = "mother-in-law";
        String expectedText = "othermay-inway-awlay";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordWithWhiteSpaces_shouldKeepTheFormat() {
        String text = "   \t\n";
        assertThat(service.translate(text)).isEqualTo(text);
    }

    /**
     * Test cases for not modifiable words plus cases with way
     */
    @Test
    void translate_givenWordNotStartWithVowelNotConsonant_shouldNotBeModified() {
        String text = "\"Lorem\"";
        assertThat(service.translate(text)).isEqualTo(text);
    }

    @Test
    void translate_givenWordContains2TimesWayButSuffixIsWay_shouldNotBeModified() {
        String text = "wayway";
        assertThat(service.translate(text)).isEqualTo(text);
    }

    @Test
    void translate_givenWordContainsWayButNotAsSuffix_shouldBeModified() {
        String text = "way!";
        String expectedText = "ayway!";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @Test
    void translate_givenWordHasSuffixWay_shouldBeModified() {
        String text = "Way";
        String expectedText = "Ayway";
        assertThat(service.translate(text)).isEqualTo(expectedText);
    }

    @ParameterizedTest
    @CsvSource({
            "a,away", "ab,abway", "abc,abcway", "abcd, abcdway",
            "a!,away!", "a!!,away!!", "a!!!,away!!!",
            "ab!,abway!", "ab!!,abway!!", "ab!!!, abway!!!",
            "a!b,abwa!y", "a!!b, abwa!!y", "a!b!,abwa!y!", "a!!b!!,abwa!!y!!",
            "a!*,away!*", "a*b!,abwa*y!", "a!*b!,abwa!*y!"
    })
    void translate_givenWordStartsWithVowelNoCapitalisationIncluded_shouldReturnCorrectMapping(String word, String expected) {
        assertThat(service.translate(word)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "A,Away", "Ab,Abway", "aB,aBway", "AB,ABway",
            "Abc,Abcway", "aBc,aBcway", "abC,abCway", "ABc,ABcway", "aBC, aBCway", "AbC,AbCway", "ABC,ABCway",
            "Abcd,Abcdway", "aBcd,aBcdway", "abCd,abCdway", "abcD,abcDway", "ABcd,ABcdway", "AbCd,AbCdway",
            "AbcD,AbcDway", "aBCd,aBCdway", "aBcD,aBcDway", "abCD,abCDway", "ABCD,ABCDway",
            "A!!b,Abwa!!y", "a!!B,abwA!!y", "A!!B,AbwA!!y",
            "A!!!b,Abwa!!!y", "a!!!B,abwa!!!y", "A!!!B,Abwa!!!y",
            "Ab!,Abway!", "aB!,aBway!", "AB!,ABway!",
            "A!b,Abwa!y", "a!B,abWa!y", "A!B,AbWa!y",
            "A!b!,Abwa!y!", "a!B!,abWa!y!", "A!B!,AbWa!y!",
            "A!b!!, Abwa!y!!", "a!B!!,abWa!y!!", "A!B!!,AbWa!y!!",
            "A!!b!,Abwa!!y!", "a!!B!,abwA!!y!", "A!!B!,AbwA!!y!",
            "A!!b!!, Abwa!!y!!", "a!!B!!, abwA!!y!!", "A!!B!!,AbwA!!y!!",
            "A!!!b!!!,Abwa!!!y!!!", "a!!!B!!!,abwa!!!y!!!", "A!!!B!!!,Abwa!!!y!!!",
            "A!*,Away!*", "a*B!,abWa*y!", "a!*b!,abwa!*y!"
    })
    void translate_givenWordStartsWithVowelAll_shouldReturnCorrectMapping(String word, String expected) {
        assertThat(service.translate(word)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "h,hay", "he,ehay", "hel,elhay", "hell,ellhay",
            "h!,hay!", "h!!,hay!!", "h!!!,hay!!!",
            "he!,ehay!", "he!!, ehay!!", "he!!!,ehay!!!",
            "h!e,eha!y", "h!!e,eha!!y", "h!!!e,eha!!!y",
            "h!e!,eha!y!", "h!e!!,eha!y!!", "h!!e!,eha!!y!", "h!!e!!,eha!!y!!",
            "h!*,hay!*", "h*e!,eha*y!", "h!*e!,eha!*y!"
    })
    void translate_givenWordStartsWithConsonantNoCapitalisationIncluded_shouldReturnCorrectMapping(String word, String
            expected) {
        assertThat(service.translate(word)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "H,Hay", "He,Ehay", "hE,eHay", "HE,EHay",
            "Hel,Elhay", "hEl,eLhay", "heL,elHay", "HEl,ELhay", "HeL,ElHay", "hEL, eLHay", "HEL, ELHay",
            "H!,Hay!", "H!!,Hay!!", "H!!!,Hay!!!",
            "He!,Ehay!", "hE!,eHay!", "HE!,EHay!",
            "H!e,Eha!y", "h!E,ehA!y", "H!E, EhA!y",
            "He!!,Ehay!!", "hE!!,eHay!!", "HE!!,EHay!!",
            "H!!e,Eha!!y", "h!!E, eha!!y", "H!!E,Eha!!y"
    })
    void translate_givenWordStartsWithConsonantAll_shouldReturnCorrectMapping(String word, String expected) {
        assertThat(service.translate(word)).isEqualTo(expected);
    }
}
