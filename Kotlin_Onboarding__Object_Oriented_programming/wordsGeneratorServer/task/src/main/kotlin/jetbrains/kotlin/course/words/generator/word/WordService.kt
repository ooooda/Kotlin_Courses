package jetbrains.kotlin.course.words.generator.word

import jetbrains.kotlin.course.words.generator.util.words
import org.springframework.stereotype.Service

@Service
class WordService {
    companion object {
        val numberOfWords: Int = words.size
        private val previousWords = mutableMapOf<String, MutableList<Word>>()
    }

    private fun String.groupByLetters() = this.groupingBy { it }.eachCount()

    fun generateNextWord(): Word {
        require(words.isNotEmpty()) { "No words in the list" }
        return Word(words.removeFirst())
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        if (newWord.isEmpty()) {
            return false
        }

        val keywordFreq = keyWord.groupByLetters()
        val newWordFreq = newWord.groupByLetters()

        return newWordFreq.all { (symbol, count) ->
            keywordFreq[symbol]?.let { it >= count } ?: false
        }
    }

    > Anastasia Birillo:
    fun isNewWord(keyWord: String, newWord: String) = previousWords.putIfAbsent(keyWord, mutableListOf(Word(newWord)))?.let { words ->
        val word = Word(newWord)
        (word !in words).also {
            if (it) {
                words.add(word)
            }
        }
    } ?: true

}
