package de.wolle.anagrams

/**
 * Checks if a word or phrase is an anagram of another one.
 *
 * The anagram of a word or phrase (the "subject") is formed by rearranging the letters into another word or phrase;
 * only letters are taken into consideration (case is ignored).
 *
 * @param subject the word or phrase to check against
 * @return true if this word or phrase is an anagram of subject, else false
 * @see <a href="https://en.wikipedia.org/wiki/Anagram">Wikipedia</a>
 */
infix fun String.isAnagramOf(subject: String): Boolean =
    this.charCounts() == subject.charCounts()

/**
 * Checks if a phrase is an anagram of another one.
 *
 * The anagram of a phrase (the "subject") is formed by rearranging the letters into another phrase (where "phrase"
 * is the combination of all strings in the given sequence); only letters are taken into consideration (case is
 * ignored).
 *
 * @param subject the phrase to check against
 * @return true if this phrase is an anagram of subject, else false
 * @see String.isAnagramOf
 * @see <a href="https://en.wikipedia.org/wiki/Anagram">Wikipedia</a>
 */
infix fun Sequence<String>.isAnagramOf(subject: Sequence<String>): Boolean =
    this.charCounts() == subject.charCounts()


typealias CharCounts = Map<Char, Int>

private fun String.charCounts(): CharCounts =
    this
        .filter(Char::isLetter)
        .lowercase()
        .countChars()

private fun String.countChars(): CharCounts =
    this
        .groupingBy { it }
        .eachCount()

private val EmptyCharCounts: CharCounts = ('a'..'z').associateWith { 0 }

internal fun Sequence<String>.charCounts(): CharCounts =
    this.fold(EmptyCharCounts) { currentCharCounts, line ->
        currentCharCounts + line.charCounts()
    }

private infix operator fun CharCounts.plus(that: CharCounts): CharCounts =
    this.map { (char, count) -> char to count + that.getOrDefault(char, 0) }
        .toMap()
