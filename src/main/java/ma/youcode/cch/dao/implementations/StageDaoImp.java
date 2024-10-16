package ma.youcode.cch.dao.implementations;

import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import ma.youcode.cch.dao.interfaces.StageDao;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class StageDaoImp extends GenericDaoImp<Stage, UUID> implements StageDao {

    public StageDaoImp(){super(Stage.class);}

}
