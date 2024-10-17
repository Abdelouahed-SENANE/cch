package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.dao.interfaces.CompetitionDao;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.service.interfaces.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CompetitionServiceImp implements CompetitionService {

    private final CompetitionDao competitionDao;
    public CompetitionServiceImp(CompetitionDao competitionDao){this.competitionDao = competitionDao;}

    @Override
    public Competition saveCompetition(Competition competition) {
        return competitionDao.save(competition);
    }

    @Override
    public Competition updateCompetition(Competition competition) {
        return competitionDao.update(competition);
    }

    @Override
    public Competition deleteCompetition(Competition competition) {
        return competitionDao.delete(competition);
    }

    @Override
    public Set<Competition> findAllCompetitions() {
        return competitionDao.findAll();
    }
}
