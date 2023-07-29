package jetbrains.kotlin.course.codenames.card

enum class CardState {
    Front,
    Back
}

interface CardData

data class WordCardData(val word: String): CardData

data class Card(val data: CardData, val state: CardState)