package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {
    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        require(result.isNotEmpty()) { "The result is empty!" }
        require(result.all { TeamService.teamsStorage.containsKey(it.id) }) { "Some teams is not in a team storage!" }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> {
        return gameHistory.asReversed()
    }
}

