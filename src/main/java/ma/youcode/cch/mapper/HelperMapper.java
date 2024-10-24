package ma.youcode.cch.mapper;

import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.TeamService;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public  class HelperMapper {

    private final TeamService teamService;
    public HelperMapper(TeamService teamService) {
        this.teamService = teamService;
    }


    @Named("teamIdToTeam")
     public Team map(UUID teamId) {
        return teamId != null ? teamService.getTeamById(teamId) : null;
    }


}
