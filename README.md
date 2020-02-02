# Pig Latin Task :rocket: 
Translate a string into `pig-latin` using the rules as stated in the description of the problem sent.

## Usage
Java 11 is required. Tests can be run as: 
```
mvn clean test
```

## Algorithm
For each word of size n:
* Time complexity = O(n).
* Space complexity = O(n). It allocates a new word to create the transformation plus O(3), 
3 the size of the suffix. This gives total O(n).

## Assumptions
* If a word doesn't start with vowel nor consonant, the same word is returned.
* The translated text keeps the format of the original input text.
* Letters Y and y are considered consonants.
* Hyphen is considered to be the minus (-) character. This is called hyphen-minus. Note: The actual hyphen character has a different unicode mapping and is not taken into account.
* Not all the punctuations characters are included.
