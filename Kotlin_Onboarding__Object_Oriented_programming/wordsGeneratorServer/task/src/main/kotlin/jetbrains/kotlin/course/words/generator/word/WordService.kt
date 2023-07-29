package jetbrains.kotlin.course.words.generator.word

import jetbrains.kotlin.course.words.generator.util.words
import org.springframework.stereotype.Service

@Service
class WordService {
    companion object {
        val numberOfWords: Int = words.size
        private val previousWords = mutableMapOf<String, MutableList<Word>>()
    }

    fun generateNextWord(): Word {
        require(words.isNotEmpty()) { "No words in the list" }
        return Word(words.removeFirst())
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        if (newWord.isEmpty()) {
            return false
        }

        val keywordFreq = keyWord.groupingBy { it }.eachCount()
        val newWordFreq = newWord.groupingBy { it }.eachCount()

        return newWordFreq.all { (symbol, count) ->
            keywordFreq[symbol]?.let { it >= count } ?: false
        }
    }

    fun isNewWord(keyWord: String, newWord: String): Boolean {
        if (!previousWords.containsKey(keyWord)) {
            previousWords[keyWord] = mutableListOf(Word(newWord))
            return true
        }

        return if (previousWords[keyWord]?.contains(Word(newWord)) == true) {
            false
        } else {
            previousWords[keyWord]?.add(Word(newWord))
            true
        }
    }
}
