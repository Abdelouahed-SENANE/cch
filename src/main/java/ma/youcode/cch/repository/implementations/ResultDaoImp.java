package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.repository.interfaces.ResultDao;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.entity.embedded.StageResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDaoImp extends GenericDaoImp<StageResult, StageResultId> implements ResultDao {

    public ResultDaoImp() {
        super(StageResult.class);
    }
}
