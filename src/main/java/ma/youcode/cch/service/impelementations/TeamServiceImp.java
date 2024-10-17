package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.dao.interfaces.CyclistDao;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.dao.interfaces.TeamDao;
import ma.youcode.cch.service.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class TeamServiceImp implements TeamService {

    private final TeamDao teamDao;
    public TeamServiceImp(TeamDao teamDao) {this.teamDao = teamDao;}

    @Override
    public Team saveTeam(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamDao.update(team);
    }

    @Override
    public Team deleteTeam(Team team) {
        return teamDao.delete(team);
    }

    @Override
    public Set<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @Override
    public Team findTeam(UUID id) {
        return null;
    }
}
