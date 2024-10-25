package ma.youcode.cch.repository.implementations;

import ma.youcode.cch.repository.interfaces.GeneralResultDao;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GeneralResultDaoImp extends GenericDaoImp<GeneralResult, GeneralResultId> implements GeneralResultDao {

    public GeneralResultDaoImp() {
        super(GeneralResult.class);
    }



//

}
