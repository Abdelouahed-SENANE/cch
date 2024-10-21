package ma.youcode.cch.daos.implementations;

import ma.youcode.cch.daos.interfaces.StageDao;
import ma.youcode.cch.entity.Stage;
import ma.youcode.cch.generic.implementations.GenericDaoImp;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public class StageDaoImp extends GenericDaoImp<Stage, UUID> implements StageDao {

    public StageDaoImp(){super(Stage.class);}

//    @Override
//    @Transactional
//    public Optional<Stage> findStageOrderedByDuration(UUID id) {
//
//        Session session = sessionFactory.getCurrentSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Stage> stageQuery = builder.createQuery(Stage.class);
//
//        Root<Stage> stageRoot = stageQuery.from(Stage.class);
//        stageQuery.select(stageRoot).where(builder.equal(stageRoot.get("stageId") , id));
//
//        Stage stage = session.createQuery(stageQuery).getSingleResult();
//        if (stage != null) {
//
//        }
//
//    }
}
