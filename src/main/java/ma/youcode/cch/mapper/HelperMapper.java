package ma.youcode.cch.mapper;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.CompetitionService;
import ma.youcode.cch.service.interfaces.TeamService;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public  class HelperMapper {

    private final TeamService teamService;
    private final CompetitionService competitionService;

    public HelperMapper(TeamService teamService , CompetitionService competitionService) {
        this.teamService = teamService;
        this.competitionService = competitionService;

    }


    @Named("teamIdToTeam")
     public Team mapToTeam(UUID teamId) {
        return teamId != null ? teamService.getTeamById(teamId) : null;
    }

    @Named("competitionIdToCompetition")
    public Competition mapToCompetition(UUID teamId) {
        return teamId != null ? competitionService.getCompetitionById(teamId).orElse(null) : null;
    }

}
