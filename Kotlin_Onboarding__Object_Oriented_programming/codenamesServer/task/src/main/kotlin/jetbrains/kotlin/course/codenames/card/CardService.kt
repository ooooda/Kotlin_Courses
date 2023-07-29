package jetbrains.kotlin.course.codenames.card

import jetbrains.kotlin.course.codenames.utils.Utils.TOTAL_NUMBER
import jetbrains.kotlin.course.codenames.utils.words
import org.springframework.stereotype.Service

@Service
class CardService {
    fun generateWordsCards(): List<Card> {
        require(words.size >= TOTAL_NUMBER) { "Incorrect amount of words!" }
        val shuffledWords = words.shuffled()
        val cards = mutableListOf<Card>()
        shuffledWords.take(TOTAL_NUMBER).forEach { word ->
            cards.add(Card(WordCardData(word), CardState.Front))
        }
        words = shuffledWords.drop(TOTAL_NUMBER)
        return cards
    }
}
