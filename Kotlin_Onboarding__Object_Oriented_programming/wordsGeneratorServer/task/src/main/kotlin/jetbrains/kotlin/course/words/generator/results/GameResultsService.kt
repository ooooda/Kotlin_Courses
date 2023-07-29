package jetbrains.kotlin.course.words.generator.results

import jetbrains.kotlin.course.words.generator.team.Team
import jetbrains.kotlin.course.words.generator.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        val gameHistory = mutableListOf<GameResult>()
    }

    fun saveGameResults(result: GameResult): Unit {
        require(result.isNotEmpty()) { "The result is empty!" }
        require(result.all { TeamService.teamsStorage.containsKey(it.id) }) { "Some teams is not in a team storage!" }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        return gameHistory.asReversed()
    }
}

