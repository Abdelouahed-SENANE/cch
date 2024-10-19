package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.daos.interfaces.ResultDao;
import ma.youcode.cch.entity.Result;
import ma.youcode.cch.entity.embedded.ResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDaoImp extends GenericDaoImp<Result, ResultId> implements ResultDao {

    public ResultDaoImp() {
        super(Result.class);
    }
}
