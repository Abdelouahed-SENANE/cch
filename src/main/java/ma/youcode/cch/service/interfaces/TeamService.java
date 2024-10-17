package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.entity.Team;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TeamService {

    Team saveTeam(Team team);
    Team updateTeam(Team team);
    Team deleteTeam(Team team);
    Set<Team> getAllTeams();
    Team findTeam(UUID id);
}
