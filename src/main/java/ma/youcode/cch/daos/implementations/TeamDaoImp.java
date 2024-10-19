package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.entity.Team;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.daos.interfaces.TeamDao;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TeamDaoImp extends GenericDaoImp<Team, UUID> implements TeamDao {

    public TeamDaoImp() {
        super(Team.class);
    }
}
