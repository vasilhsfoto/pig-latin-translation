# Pig Latin Task

Translate a String into `pig-latin` using the rules as stated in the description of the problem sent.

## Usage
Java 11 is required. For running the tests you could use: 
```
mvn clean test
```

## Algorithm
The provided implementation has the following properties:

For each word of size n:

* time complexity = O(n)

* space complexity = O(n) because it creates a new word to store the transformation plus O(3), 
3 the size of the suffix. This gives total O(n)

## Assumptions
* If a word doesn't start with vowel nor consonant, the same word is returned.
* The translated text keeps the format of the original input text.
* `y` is considered a consonant.
* Hyphen is considered to be the minus (`-`) character. This is called hyphen-minus. Note: Hyphen character has a different unicode mapping and is not taken into account.
* Not all the punctuations characters are included.

## Notes
I found the problem interesting from an algorithmic stand point. OOP design patterns etc were not the focus
