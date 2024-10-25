package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.repository.interfaces.StageResultDao;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.entity.embedded.StageResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

@Repository
public class StageResultDaoImp extends GenericDaoImp<StageResult, StageResultId> implements StageResultDao {

    public StageResultDaoImp() {
        super(StageResult.class);
    }
}
