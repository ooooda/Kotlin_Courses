package jetbrains.kotlin.course.words.generator.team

import org.springframework.stereotype.Service

@Service
class TeamService {
    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = List(teamsNumber) { Team() }
        teams.forEach { teamsStorage.putIfAbsent(it.id, it) }
        return teams
    }

    companion object {
        val teamsStorage = mutableMapOf<Identifier, Team>()
    }
}
