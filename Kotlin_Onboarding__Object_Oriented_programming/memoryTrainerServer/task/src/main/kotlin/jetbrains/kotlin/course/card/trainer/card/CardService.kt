package jetbrains.kotlin.course.card.trainer.card

import jetbrains.kotlin.course.card.trainer.util.countries
import org.springframework.stereotype.Service

@Service
class CardService {

    companion object {
        private val randomCardGenerator: CardSequenceGenerator = CardSequenceGenerator {
            countries.map{(capital, country) -> Card(Front(capital), Back(country))}.shuffled()
        }

        private fun generateNewCardsSequence() = randomCardGenerator.generateCards().toMutableList()

        private var cards = generateNewCardsSequence()
    }

    fun getNextCard(): Card {
        require(cards.isNotEmpty()) {" No cards "}
        val firstCard = cards.first()
        cards.removeFirst()
        return firstCard
    }

    fun startNewGame(): Card {
        cards = generateNewCardsSequence()
        return getNextCard()
    }
}
