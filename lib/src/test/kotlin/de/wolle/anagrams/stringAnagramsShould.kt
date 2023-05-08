package de.wolle.anagrams

import io.kotest.matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class TwoStringsShould {

    @ParameterizedTest
    @ValueSource(strings = ["ana", "be123", "#.k|@!"])
    fun `be anagrams if identical`(subject: String){
        subject shouldBeAnagramOf subject
    }

    @Test
    fun `be anagrams if only case differs`() {
        "ANa" shouldBeAnagramOf "ana"
    }

    @ParameterizedTest
    @MethodSource("anagramsWithWhitespaces")
    fun `be anagrams ignoring whitespaces`(subject: String, anagram: String) {
        anagram shouldBeAnagramOf subject
    }

    @Test
    fun `be anagrams ignoring everything than letters`() {
        "3n\na,.n#a6" shouldBeAnagramOf "1an7na"
    }

    @Test
    fun `not be anagrams if containing different letters`() {
        "this is" shouldNotBeAnagramOf "not an anagram"
    }

    @ParameterizedTest
    @MethodSource("singleWordAnagrams")
    fun `be anagrams`(subject: String, anagram: String) {
        anagram shouldBeAnagramOf subject
    }

    companion object {

        @JvmStatic
        fun singleWordAnagrams(): List<Arguments> =
            listOf(
                arguments("restful", "fluster"),
                arguments("restful", "fluster"),
                arguments("cheater", "teacher"),
                arguments("Santa", "Satan"),
            )

        @JvmStatic
        fun anagramsWithWhitespaces(): List<Arguments> =
            listOf(
                arguments("adultery", "true lady"),
                arguments("forty five", "over fifty"),
                arguments("William Shakespeare", "I am a weakish speller"),
                arguments("Madam Curie", "Radium came"),
                arguments("George Bush", "He bugs Gore"),
                arguments("Tom Marvolo Riddle", "I am Lord Voldemort"),
            )
    }
}

private fun anagramOf(subject: String): Matcher<String> =
    Matcher { anagram ->
        MatcherResult(
            anagram isAnagramOf subject,
            { "'${anagram}' should be an anagram of '${subject}'" },
            { "'${anagram}' should not be an anagram of '${subject}'" },
        )
    }

private infix fun String.shouldBeAnagramOf(subject: String): String {
    this shouldBe anagramOf(subject)
    return this
}

private infix fun String.shouldNotBeAnagramOf(subject: String): String {
    this shouldNotBe anagramOf(subject)
    return this
}
