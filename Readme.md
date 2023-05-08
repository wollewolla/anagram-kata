# Task

Write a program that checks if two texts are anagrams of each other.
Please use the english wikipedia entry for the definition of an anagram.

# Solution

Implemented as library. To execute the tests, run

```shell
./gradlew test
```
or (if docker available)

```shell
docker build .
```

**Notes**

- two texts are anagrams if each **letter** occurs the same number of times - case and non-letter characters are ignored
- to check larger texts (like files), an anagram detection of string sequences can be used (see test in `sequenceAnagramsShould.kt`)
- the texts for the huge anagram test are taken from [anagrammy.com](https://www.anagrammy.com/anagrams/faq4.html)