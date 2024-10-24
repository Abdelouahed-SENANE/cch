package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.dtos.team.CreateTeamDTO;
import ma.youcode.cch.dtos.team.TeamResponseDTO;
import ma.youcode.cch.entity.Team;

import java.util.Set;
import java.util.UUID;

public interface TeamService {

    TeamResponseDTO createTeam(CreateTeamDTO createTeamDTO);
    TeamResponseDTO updateTeam(CreateTeamDTO createTeamDTO);
    TeamResponseDTO deleteTeam(UUID teamId);
    Set<Team> getAllTeams();
    TeamResponseDTO getTeam(UUID id);
}
