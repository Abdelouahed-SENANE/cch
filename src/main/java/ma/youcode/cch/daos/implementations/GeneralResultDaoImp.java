package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.daos.interfaces.GeneralResultDao;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralResultDaoImp extends GenericDaoImp<GeneralResult, GeneralResultId> implements GeneralResultDao {

    public GeneralResultDaoImp() {
        super(GeneralResult.class);
    }
}
