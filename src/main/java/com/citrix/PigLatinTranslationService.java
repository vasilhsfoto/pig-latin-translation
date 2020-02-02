package com.citrix;

import java.util.LinkedList;
import java.util.Queue;

import static com.citrix.CharacterUtils.isConsonant;
import static com.citrix.CharacterUtils.isPunctuation;
import static com.citrix.CharacterUtils.isSplittable;
import static com.citrix.CharacterUtils.isVowel;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class PigLatinTranslationService {
    private static final String AY = "ay";
    private static final String WAY = "way";

    public String translate(String text) {
        if (text.isBlank()) {
            return text;
        }

        var translatedTextBuilder = new StringBuilder(text.length());
        var wordBuilder = new StringBuilder();

        for (int t = 0; t < text.length(); t++) {
            char c = text.charAt(t);

            if (!isSplittable(c)) {
                wordBuilder.append(c);
            } else {
                if (wordBuilder.length() != 0) {
                    translatedTextBuilder.append(transform(wordBuilder.toString()));
                    wordBuilder = new StringBuilder();
                }
                translatedTextBuilder.append(c);
            }
        }

        if (wordBuilder.length() != 0) {
            translatedTextBuilder.append(transform(wordBuilder.toString()));
        }

        return translatedTextBuilder.toString();
    }

    private String transform(String word) {
        if (word.endsWith(WAY)) {
            return word;
        }

        if (isConsonant(word.charAt(0))) {
            // we need to put the first character in suffix not to lose the positions of the punctuations
            return transform(word, word.substring(1), word.charAt(0) + AY);
        }

        if (isVowel(word.charAt(0))) {
            return transform(word, word, WAY);
        }

        return word;
    }

    private String transform(String original, String prefix, String suffix) {
        char[] result = new char[prefix.length() + suffix.length()];

        Queue<Character> remainingChars = new LinkedList<>();
        for (int s = suffix.length() - 1; s >= 0; s--) {
            remainingChars.add(suffix.charAt(s));
        }

        int r = result.length - 1;
        for (int p = prefix.length() - 1; p >= 0; p--) {
            char c = prefix.charAt(p);

            if (isPunctuation(c)) {
                result[r] = c;
            } else {
                char toAdd = remainingChars.remove();
                result[r] = changeCaseIfNeeded(original, r, toAdd);
                remainingChars.add(c);
            }
            r--;
        }

        while (!remainingChars.isEmpty()) {
            char toAdd = remainingChars.remove();
            result[r] = changeCaseIfNeeded(original, r, toAdd);
            r--;
        }

        return new String(result);
    }

    private char changeCaseIfNeeded(String original, int resultIndex, char c) {
        if (resultIndex <= original.length() - 1) {
            return (isUpperCase(original.charAt(resultIndex))) ? toUpperCase(c) : toLowerCase(c);
        }
        return c;
    }
}
