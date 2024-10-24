package ma.youcode.cch.mapper.helpers;

import ma.youcode.cch.entity.Team;
import ma.youcode.cch.service.interfaces.TeamService;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MapperHelper {

    private final TeamService teamService;
    public MapperHelper(TeamService teamService) {
        this.teamService = teamService;
    }


    @Named("teamIdToTeam")
     public Team map(UUID teamId) {
        return teamId != null ? teamService.getTeamById(teamId) : null;
    }


}
