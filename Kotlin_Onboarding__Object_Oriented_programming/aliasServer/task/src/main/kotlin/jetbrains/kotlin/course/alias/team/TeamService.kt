package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service

@Service
class TeamService {
    private val identifierFactory: IdentifierFactory = IdentifierFactory()

    companion object {
        val teamsStorage = mutableMapOf<Identifier, Team>()
    }

    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val teams = List(teamsNumber) { Team(identifierFactory.uniqueIdentifier()) }
        teamsStorage.putAll(teams.associateBy { it.id })
        return teams
    }
}

