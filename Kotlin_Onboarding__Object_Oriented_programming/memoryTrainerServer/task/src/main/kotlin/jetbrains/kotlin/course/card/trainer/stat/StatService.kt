package jetbrains.kotlin.course.card.trainer.stat

import jetbrains.kotlin.course.card.trainer.card.Back
import org.springframework.stereotype.Service

@Service
class StatService {

    companion object {
        private val history = mutableListOf<Stat>()
    }

    fun getHistory(): MutableList<Stat> = history.asReversed()

    fun save(knownBacks: List<String>, unknownBacks: List<String>): Unit {
        history.add(Stat(knownBacks.map{ Back(it) }, unknownBacks.map{ Back(it) }))
    }
}
