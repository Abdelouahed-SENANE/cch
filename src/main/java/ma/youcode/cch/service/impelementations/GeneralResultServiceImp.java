package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.daos.interfaces.CompetitionDao;
import ma.youcode.cch.daos.interfaces.CyclistDao;
import ma.youcode.cch.daos.interfaces.GeneralResultDao;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Cyclist;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.service.interfaces.GeneralResultService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GeneralResultServiceImp implements GeneralResultService {

    private final GeneralResultDao generalResultDao;
    private final CyclistDao cyclistDao;
    private final CompetitionDao competitionDao;

    public GeneralResultServiceImp(GeneralResultDao generalResultDao , CyclistDao cyclistDao , CompetitionDao competitionDao) {
        this.generalResultDao = generalResultDao;
        this.competitionDao = competitionDao;
        this.cyclistDao = cyclistDao;
    }

    @Override
    public GeneralResult createGeneralResult(GeneralResult generalResult) {

        Optional<Cyclist> cyclist = cyclistDao.findById(generalResult.getGeneralResultId().getCyclistId());
        Optional<Competition> competition = competitionDao.findById(generalResult.getGeneralResultId().getCompetitionId());



        if (cyclist.isPresent() && competition.isPresent()) {
            return generalResultDao.save(generalResult);
        }else {
            System.out.println("Not Exists");
        }

        return null;
    }

    @Override
    public GeneralResult updateGeneralResult(GeneralResult generalResult) {
        return generalResultDao.update(generalResult);
    }

    @Override
    public GeneralResult deleteGeneralResult(GeneralResult generalResult) {
        return generalResultDao.delete(generalResult);
    }

    @Override
    public Set<GeneralResult> getAllGeneralResults() {
        return generalResultDao.findAll();
    }


}
