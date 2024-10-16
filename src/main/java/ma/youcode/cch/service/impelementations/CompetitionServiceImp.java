package ma.youcode.cch.service.impelementations;

import ma.youcode.cch.dao.interfaces.CompetitionDao;
import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.service.interfaces.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompetitionServiceImp implements CompetitionService {

    private final CompetitionDao competitionDao;
    public CompetitionServiceImp(CompetitionDao competitionDao){this.competitionDao = competitionDao;}

    public void test(){

        System.out.println("Hello Competition service" + competitionDao.findAll());
    }

}
