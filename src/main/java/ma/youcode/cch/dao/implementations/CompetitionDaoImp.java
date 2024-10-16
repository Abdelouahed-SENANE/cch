package ma.youcode.cch.dao.implementations;

import ma.youcode.cch.entity.Competition;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.dao.interfaces.CompetitionDao;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CompetitionDaoImp extends GenericDaoImp<Competition, UUID> implements CompetitionDao {

    public CompetitionDaoImp() {
        super(Competition.class);
    }


}
