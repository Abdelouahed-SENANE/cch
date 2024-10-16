package ma.youcode.cch.dao.implementations;

import ma.youcode.cch.entity.Team;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.dao.interfaces.TeamDao;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TeamDaoImp extends GenericDaoImp<Team, UUID> implements TeamDao {

    public TeamDaoImp() {
        super(Team.class);
    }
}
