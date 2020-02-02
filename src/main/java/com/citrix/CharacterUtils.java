package com.citrix;

import java.util.Set;

public class CharacterUtils {
    private static final Set<Character> VOWELS =
            Set.of('A', 'E', 'I', 'O', 'U',
                    'a', 'e', 'i', 'o', 'u');
    private static final Set<Character> CONSONANTS =
            Set.of('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
                    'V', 'W', 'X', 'Y', 'Z',
                    'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't',
                    'v', 'w', 'x', 'y', 'z');

    private static final Set<Character> PUNCTUATIONS = Set.of('.', ':', ';', '!', '"', '\'', '*');

    public static boolean isSplittable(char c) {
        return ((Character.isWhitespace(c)) || (isHyphen(c)));
    }

    public static boolean isVowel(char c) {
        return VOWELS.contains(c);
    }

    public static boolean isConsonant(char c) {
        return CONSONANTS.contains(c);
    }

    public static boolean isPunctuation(char c) {
        return PUNCTUATIONS.contains(c);
    }

    private static boolean isHyphen(char c) {
        return c == '-';
    }
}
