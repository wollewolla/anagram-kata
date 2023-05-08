import de.wolle.anagrams.isAnagramOf
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import java.io.File

class TwoSequencesShould {

    @Test
    fun `be anagrams when empty`() {
        emptySequence<String>() shouldBeAnagramOf emptySequence()
    }

    @Test
    fun `be anagrams`() {
        fileInResources("battle_of_the_books.txt").useLines { subject ->
            fileInResources("tempest_of_the_tomes.txt").useLines { anagram ->
                anagram shouldBeAnagramOf subject
            }
        }
    }

    @Test
    fun `not be anagrams`() {
        sequenceOf("abc") shouldNotBeAnagramOf sequenceOf("zfa", "cba")
    }

    private fun fileInResources(filename: String): File =
        File(TwoSequencesShould::class.java.classLoader.getResource(filename)!!.file)
}

private infix fun Sequence<String>.shouldBeAnagramOf(that: Sequence<String>): Sequence<String> {
    this shouldBe anagramOf(that)
    return this
}

private infix fun Sequence<String>.shouldNotBeAnagramOf(that: Sequence<String>): Sequence<String> {
    this shouldNotBe anagramOf(that)
    return this
}

private fun anagramOf(subject: Sequence<String>): Matcher<Sequence<String>> =
    Matcher { anagram ->
        MatcherResult(
            anagram isAnagramOf subject,
            { "sequences should be anagrams" },
            { "sequences should not be anagrams" },
        )
    }

