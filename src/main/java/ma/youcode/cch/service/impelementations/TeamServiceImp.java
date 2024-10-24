package ma.youcode.cch.service.impelementations;

import jakarta.persistence.EntityNotFoundException;
import ma.youcode.cch.daos.interfaces.TeamDao;
import ma.youcode.cch.dtos.team.CreateTeamDTO;
import ma.youcode.cch.dtos.team.TeamResponseDTO;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.mapper.TeamMapper;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class TeamServiceImp implements TeamService {

    private final TeamDao teamDao;
    private final TeamMapper teamMapper;

    public TeamServiceImp(TeamDao teamDao, TeamMapper teamMapper) {
        this.teamDao = teamDao;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamResponseDTO createTeam(CreateTeamDTO createTeamDTO) {
        Team saved = teamMapper.toTeamEntity(createTeamDTO);
         saved = teamDao.save(saved);
        return teamMapper.toResponseDTO(saved);
    }

    @Override
    public TeamResponseDTO updateTeam(CreateTeamDTO createTeamDTO) {

        if (isTeamExist(createTeamDTO.getTeamId())) {
            throw new EntityNotFoundException("Team Not Found");
        }

        Team updated = teamMapper.toTeamEntity(createTeamDTO);
        updated = teamDao.update(updated);

        return teamMapper.toResponseDTO(updated);
    }

    @Override
    public TeamResponseDTO deleteTeam(UUID teamId) {
        if (!isTeamExist(teamId)) {
            throw new EntityNotFoundException("Team Not Found");
        }

        Team existingTeam = this.getTeamById(teamId);
        existingTeam = teamDao.delete(existingTeam);

        return teamMapper.toResponseDTO(existingTeam);
    }

    @Override
    public Set<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @Override
    public TeamResponseDTO getTeam(UUID teamId) {
        Optional<Team> optionalTeam = teamDao.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new EntityNotFoundException("Team Not Found");
        }
        return teamMapper.toResponseDTO(optionalTeam.get());
    }

    private boolean isTeamExist(UUID teamId) {
        return teamDao.existsById(teamId);
    }

    private Team getTeamById(UUID teamId) {
        Optional<Team> optionalTeam = teamDao.findById(teamId);
        return optionalTeam.orElse(null);
    }
}
