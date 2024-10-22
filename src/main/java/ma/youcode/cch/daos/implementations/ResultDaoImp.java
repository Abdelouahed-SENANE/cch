package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.daos.interfaces.ResultDao;
import ma.youcode.cch.entity.StageResult;
import ma.youcode.cch.entity.embedded.ResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDaoImp extends GenericDaoImp<StageResult, ResultId> implements ResultDao {

    public ResultDaoImp() {
        super(StageResult.class);
    }
}
