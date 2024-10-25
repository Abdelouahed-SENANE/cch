package ma.youcode.cch.service.interfaces;

import ma.youcode.cch.DTOs.team.CreateTeamDTO;
import ma.youcode.cch.DTOs.team.TeamResponseDTO;
import ma.youcode.cch.entity.Team;

import java.util.Set;
import java.util.UUID;

public interface TeamService {

    TeamResponseDTO createTeam(CreateTeamDTO createTeamDTO);
    TeamResponseDTO updateTeam(CreateTeamDTO createTeamDTO , UUID teamId);
    TeamResponseDTO deleteTeam(UUID teamId);
    Set<Team> getAllTeams();
    TeamResponseDTO getTeam(UUID id);
    Team getTeamById(UUID teamId);
}
