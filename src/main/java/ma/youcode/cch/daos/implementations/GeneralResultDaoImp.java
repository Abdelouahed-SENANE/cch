package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.daos.interfaces.GeneralResultDao;
import ma.youcode.cch.entity.GeneralResult;
import ma.youcode.cch.entity.embedded.GeneralResultId;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class GeneralResultDaoImp extends GenericDaoImp<GeneralResult, GeneralResultId> implements GeneralResultDao {

    public GeneralResultDaoImp() {
        super(GeneralResult.class);
    }



    public Optional<GeneralResult> findByGeneralResultId(GeneralResultId generalResultId) {
        if (generalResultId == null) {
            return null;
        }
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(GeneralResult.class , generalResultId));
    }

}
