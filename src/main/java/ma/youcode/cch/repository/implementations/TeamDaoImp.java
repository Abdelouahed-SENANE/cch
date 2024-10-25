package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.repository.interfaces.TeamDao;
import ma.youcode.cch.entity.Team;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TeamDaoImp extends GenericDaoImp<Team, UUID> implements TeamDao {

    public TeamDaoImp() {
        super(Team.class);
    }
}
